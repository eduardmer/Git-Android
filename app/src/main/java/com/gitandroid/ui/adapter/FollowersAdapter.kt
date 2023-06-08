package com.gitandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core_model.Follower
import com.gitandroid.databinding.FollowersItemBinding

class FollowersAdapter : ListAdapter<Follower, FollowersAdapter.FollowersViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding = FollowersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FollowersViewHolder(val binding: FollowersItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Follower) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Follower>() {
        override fun areItemsTheSame(oldItem: Follower, newItem: Follower): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Follower, newItem: Follower): Boolean {
            return oldItem == newItem
        }
    }

}