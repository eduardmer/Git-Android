package com.gitandroid.utils

import android.widget.ProgressBar
import androidx.core.view.isVisible

fun ProgressBar.showProgressBar(isVisible: Boolean) {
    this.isVisible = isVisible
}