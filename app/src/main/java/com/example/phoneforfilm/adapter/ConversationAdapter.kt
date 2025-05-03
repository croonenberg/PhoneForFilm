package com.example.phoneforfilm.adapter

import android.text.format.DateFormat
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
        fun bind(convo: Conversation) = with(binding) {
            tvContactName.text  = convo.contactName
            tvLastMessage.text  = convo.lastMessage
            tvTimestamp.text    = DateFormat.format("HH:mm", convo.lastMessageTime)
            root.setOnClickListener { onClick(convo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemConversationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    fun submitList(newItems: List<Conversation>) {
        items = newItems
        notifyDataSetChanged()
    }
}