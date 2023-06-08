package com.gitandroid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core_domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val isLogged = userRepository.user.map {
        it.token.isNotEmpty()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        true
    )

}