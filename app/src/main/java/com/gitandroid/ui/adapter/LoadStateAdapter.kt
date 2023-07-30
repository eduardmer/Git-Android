package com.gitandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gitandroid.databinding.ViewLoadStateBinding

class LoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<com.gitandroid.ui.adapter.LoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ViewLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(retry, loadState)
    }

    class LoadStateViewHolder(private val binding: ViewLoadStateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(retry: () -> Unit,  state: LoadState) {
            binding.apply {
                progressBar.isVisible = state is LoadState.Loading
                retryButton.isVisible = state is LoadState.Error
                errorText.isVisible = !(state as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorText.text = (state as? LoadState.Error)?.error?.message
                retryButton.setOnClickListener {
                    retry()
                }
            }
        }

    }

}