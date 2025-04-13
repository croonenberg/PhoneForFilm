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
            val textMessage = itemView.findViewById<TextView>(R.id.textMessage)
            val textStatus = itemView.findViewById<TextView>(R.id.textStatus)

            textMessage.text = message.content

            val statusSymbol = when (message.status) {
                0 -> "✓"
                1 -> "✓✓"
                2 -> "✓✓"
                else -> ""
            }

            textStatus.text = statusSymbol

            if (message.status == 2) {
                textStatus.setTextColor(itemView.context.getColor(R.color.colorAccent)) // Blauw bij gelezen
            } else {
                textStatus.setTextColor(itemView.context.getColor(R.color.textLight)) // Normale kleur
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Message, newItem: Message) = oldItem == newItem
    }
}
