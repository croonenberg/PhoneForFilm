package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ItemConversationBinding

class ConversationAdapter(
    private val conversations: List<Conversation>,
    private val contactNames: Map<Int, String>,
    private val onClick: (Conversation) -> Unit
) : RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>() {

    inner class ConversationViewHolder(val binding: ItemConversationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(conversation: Conversation) {
            binding.tvContactNameConversation.text = contactNames[conversation.contactId] ?: "Onbekend"
            binding.tvLastMessage.text = conversation.lastMessage
            binding.tvTimestamp.text = android.text.format.DateFormat.format("HH:mm", conversation.timestamp)
            binding.root.setOnClickListener { onClick(conversation) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val binding = ItemConversationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.bind(conversations[position])
    }

    override fun getItemCount(): Int = conversations.size
}
