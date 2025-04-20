package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ItemConversationBinding

class ConversationAdapter(
    private val onClick: (Conversation) -> Unit,
    private val onDelete: (Conversation) -> Unit
) : ListAdapter<Conversation, ConversationAdapter.ConversationViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val binding = ItemConversationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ConversationViewHolder(private val binding: ItemConversationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(conversation: Conversation) {
            binding.tvContactName.text = conversation.name
            binding.tvLastMessage.text = conversation.lastMessage
            binding.tvTimestamp.text = android.text.format.DateFormat.format("HH:mm", conversation.timestamp)

            binding.root.setOnClickListener { onClick(conversation) }
            binding.root.setOnLongClickListener {
                onDelete(conversation)
                true
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Conversation>() {
        override fun areItemsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
            return oldItem == newItem
        }
    }
}
