package com.gitandroid.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("image")
fun ImageView.setImage(url: String) {
    load(url)
}