package com.gitandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core_domain.use_case.GetEventsUseCase
import com.core_domain.use_case.GetFollowersUseCase
import com.core_domain.use_case.GetFollowingUseCase
import com.core_domain.use_case.GetReposUseCase
import com.core_domain.use_case.GetUserUseCase
import com.core_model.Repository
import com.core_model.Result
import com.core_model.User
import com.core_model.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    getUserUseCase: GetUserUseCase,
    getReposUseCase: GetReposUseCase,
    getEventsUseCase: GetEventsUseCase,
    getFollowersUseCase: GetFollowersUseCase,
    getFollowingUseCase: GetFollowingUseCase
) : ViewModel() {

    val userData: StateFlow<ProfileUiState> = getUserUseCase().flatMapLatest { user ->
        getReposUseCase(user).asResult().map { result ->
            when(result) {
                is Result.Success<*> -> ProfileUiState.Success(user, result.data as List<Repository>)
                is Result.Error -> ProfileUiState.Error(result.error)
                Result.Loading -> ProfileUiState.Loading
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ProfileUiState.Loading
    )

    val events = getEventsUseCase().asResult().map { result ->
        when(result) {
            is Result.Success<*> -> UiState.Success(result.data)
            is Result.Error -> UiState.Error(result.error)
            Result.Loading -> UiState.Loading
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        UiState.Loading
    )

    val followers = getFollowersUseCase().asResult().map { result ->
        when(result) {
            is Result.Success<*> -> UiState.Success(result.data)
            is Result.Error -> UiState.Error(result.error)
            Result.Loading -> UiState.Loading
        }
    }

    val following = getFollowingUseCase().asResult().map { result ->
        when(result) {
            is Result.Success<*> -> UiState.Success(result.data)
            is Result.Error -> UiState.Error(result.error)
            Result.Loading -> UiState.Loading
        }
    }

}

sealed interface ProfileUiState {
    data class Success(val user: User, val repos: List<Repository>) : ProfileUiState
    data class Error(val error: Throwable) : ProfileUiState
    object Loading : ProfileUiState
}

sealed interface UiState {
    data class Success<T>(val data: T) : UiState
    data class Error(val error: Throwable) : UiState
    object Loading : UiState
}