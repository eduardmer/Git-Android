package com.gitandroid.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core_domain.use_case.GetUserUseCase
import com.core_domain.use_case.LoginUserUseCase
import com.core_model.Result
import com.core_model.User
import com.core_model.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    getUserUseCase: GetUserUseCase) : ViewModel() {

    val uistate = getUserUseCase().asResult().map {
        when(it) {
            is Result.Success<*> -> if ((it.data as User).token.isNotEmpty()) UiState.LoggedIn
                else UiState.LoggedOut
            is Result.Error -> UiState.Error(it.error)
            Result.Loading -> UiState.Loading
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        UiState.LoggedOut
    )

    fun login(clientId: String, clientSecret: String, code: String, redirectUri: String) {
        viewModelScope.launch {
            loginUserUseCase(clientId, clientSecret, code, redirectUri)
        }
    }

}

sealed interface UiState {
    object LoggedIn : UiState
    object LoggedOut : UiState
    data class Error(val error: Throwable) : UiState
    object Loading : UiState
}