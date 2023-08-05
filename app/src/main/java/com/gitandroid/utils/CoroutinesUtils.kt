package com.gitandroid.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

inline fun <T> LifecycleOwner.collectFlow(
    flow: StateFlow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline blocked: suspend (T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            flow.collect {
                blocked(it)
            }
        }
    }
}