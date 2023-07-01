package com.gitandroid.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core_domain.use_case.GetUserUseCase
import com.core_domain.use_case.LoginUserUseCase
import com.core_model.Result
import com.core_model.User
import com.core_model.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.publish
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    getUserUseCase: GetUserUseCase) : ViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.LoggedOut)

    init {
        viewModelScope.launch {
            getUserUseCase().asResult().collect { result ->
                uiState.update {
                    when(result) {
                        is Result.Success<*> ->
                            if ((result.data as User).token.isNotEmpty()) UiState.LoggedIn
                            else UiState.LoggedOut
                        is Result.Error -> UiState.Error(result.error)
                        Result.Loading -> UiState.Loading
                    }
                }
            }
        }
    }

    fun login(clientId: String, clientSecret: String, code: String, redirectUri: String) {
        viewModelScope.launch {
            loginUserUseCase(clientId, clientSecret, code, redirectUri).asResult().collect { result ->
                uiState.update {
                    when(result) {
                        is Result.Success<*> -> UiState.LoggedOut
                        is Result.Error -> UiState.Error(result.error)
                        Result.Loading -> UiState.Loading
                    }
                }
            }
        }
    }

}

sealed interface UiState {
    object LoggedIn : UiState
    object LoggedOut : UiState
    data class Error(val error: Throwable) : UiState
    object Loading : UiState
}