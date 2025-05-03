package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ItemConversationBinding

class ConversationAdapter(
    items: List<Conversation> = emptyList(),
    private val onClick: (Conversation) -> Unit
) : ListAdapter<Conversation, ConversationAdapter.VH>(DIFF) {

    init {
        submitList(items)
    }

    inner class VH(private val binding: ItemConversationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(conv: Conversation) = with(binding) {
            tvContactName.text = conv.contactName
            tvLastMessage.text = conv.lastMessage
            root.setOnClickListener { onClick(conv) }
        }
    }

    override fun onCreateViewHolder(p: ViewGroup, v: Int): VH =
        VH(ItemConversationBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Conversation>() {
            override fun areItemsTheSame(oldItem: Conversation, newItem: Conversation) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Conversation, newItem: Conversation) = oldItem == newItem
        }
    }
}
