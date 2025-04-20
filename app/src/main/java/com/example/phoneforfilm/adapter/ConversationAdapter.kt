package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ItemConversationBinding

class ConversationAdapter(
    private val onClick: (Conversation) -> Unit,
    private val onDelete: (Conversation) -> Unit
) : RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>() {

    private val conversations = mutableListOf<Conversation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val binding = ItemConversationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.bind(conversations[position])
    }

    override fun getItemCount(): Int = conversations.size

    fun submitList(list: List<Conversation>) {
        conversations.clear()
        conversations.addAll(list)
        notifyDataSetChanged()
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
}
