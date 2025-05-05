package com.example.phoneforfilm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ItemConversationBinding
import android.text.format.DateFormat

class ChatListAdapter(
    private val onClick: (Conversation) -> Unit
) : RecyclerView.Adapter<ChatListAdapter.VH>() {

    private var items: List<Conversation> = emptyList()

    fun submitList(list: List<Conversation>) {
        items = list
        notifyDataSetChanged()
    }

    inner class VH(private val binding: ItemConversationBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                // Gebruik absoluteAdapterPosition om de juiste positie te bepalen
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick(items[position])
                }
            }
        }

        fun bind(item: Conversation) {
            binding.tvContactName.text = item.contactName
            binding.tvLastMessage.text = item.lastMessage
            binding.tvTimestamp.text = DateFormat.format("HH:mm", item.timestamp)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemConversationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
