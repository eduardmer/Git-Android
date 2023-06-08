package com.gitandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core_model.GitEvents
import com.gitandroid.databinding.EventItemBinding

class EventAdapter : ListAdapter<GitEvents, EventAdapter.EventsViewHolder>(DiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EventsViewHolder(val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GitEvents) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<GitEvents>() {
        override fun areItemsTheSame(oldItem: GitEvents, newItem: GitEvents): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GitEvents, newItem: GitEvents): Boolean {
            return oldItem == newItem
        }
    }

}