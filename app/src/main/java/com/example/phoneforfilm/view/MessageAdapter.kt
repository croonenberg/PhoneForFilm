package com.example.phoneforfilm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.model.Message

class MessageAdapter : ListAdapter<Message, MessageAdapter.MessageViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layout = if (viewType == 1) {
            R.layout.item_message_sent
        } else {
            R.layout.item_message_received
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).sentByUser) 1 else 0
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) {
            itemView.findViewById<TextView>(R.id.textMessage).text = message.content
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Message, newItem: Message) = oldItem == newItem
    }
}
