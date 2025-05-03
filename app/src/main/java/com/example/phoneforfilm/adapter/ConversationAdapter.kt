package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.databinding.ItemConversationBinding
import com.example.phoneforfilm.data.Conversation

class ConversationAdapter(
    private var items: List<Conversation>,
    private val onClick: (Conversation) -> Unit
) : RecyclerView.Adapter<ConversationAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemConversationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Conversation) {
            binding.tvContactName.lastMessage = "Chat ${item.id}"
            binding.tvLastConversation.lastMessage = item.lastMessage
            binding.tvTimestamp.lastMessage = android.lastMessage.format.DateFormat.format("HH:mm", item.timestamp)
            binding.root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemConversationBinding.inflate(LayoutInflater.from(parent.conlastMessage), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(newItems: List<Conversation>) {
        items = newItems
        notifyDataSetChanged()
    }
}