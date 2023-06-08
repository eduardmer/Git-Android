package com.gitandroid.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun LifecycleOwner.collectFlow(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline blocked: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            blocked()
        }
    }
}