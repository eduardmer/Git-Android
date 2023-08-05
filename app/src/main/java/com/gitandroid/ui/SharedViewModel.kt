package com.gitandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.core_domain.use_case.GetAuthenticatedUserUseCase
import com.core_domain.use_case.GetEventsUseCase
import com.core_domain.use_case.GetFollowersUseCase
import com.core_domain.use_case.GetFollowingUseCase
import com.core_domain.use_case.GetReposUseCase
import com.core_domain.use_case.GetStarredReposUseCase
import com.core_model.Result
import com.core_model.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    getAuthenticatedUserUseCase: GetAuthenticatedUserUseCase,
    getEventsUseCase: GetEventsUseCase,
    getFollowersUseCase: GetFollowersUseCase,
    getFollowingUseCase: GetFollowingUseCase,
    getReposUseCase: GetReposUseCase,
    getStarredReposUseCase: GetStarredReposUseCase
) : ViewModel() {

    val profileUiState: StateFlow<UiState> = getAuthenticatedUserUseCase().asResult().map { result ->
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
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        UiState.Loading
    )

    val following = getFollowingUseCase().asResult().map { result ->
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

    val repos = getReposUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        PagingData.empty()
    )

    val starredRepos = getStarredReposUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        PagingData.empty()
    )

}

sealed interface UiState {
    data class Success<T>(val data: T) : UiState
    data class Error(val error: Throwable) : UiState
    object Loading : UiState
}