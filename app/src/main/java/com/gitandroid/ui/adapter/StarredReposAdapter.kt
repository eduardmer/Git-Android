package com.gitandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.core_model.Repository
import com.gitandroid.databinding.StarredReposItemBinding

class StarredReposAdapter : PagingDataAdapter<Repository, StarredReposAdapter.StarredReposViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredReposViewHolder {
        val binding = StarredReposItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StarredReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StarredReposViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class StarredReposViewHolder(val binding: StarredReposItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Repository) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return true
        }
    }

}