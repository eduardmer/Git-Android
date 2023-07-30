package com.gitandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core_model.HomeMenu
import com.gitandroid.databinding.HomeItemBinding

class HomeMenuAdapter : ListAdapter<HomeMenu, HomeMenuAdapter.HomeMenuViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMenuViewHolder {
        val binding = HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeMenuViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeMenuViewHolder(val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeMenu) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<HomeMenu>() {
        override fun areItemsTheSame(oldItem: HomeMenu, newItem: HomeMenu): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeMenu, newItem: HomeMenu): Boolean {
            return oldItem == newItem
        }
    }

}