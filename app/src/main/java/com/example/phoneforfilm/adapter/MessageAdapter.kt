package com.example.phoneforfilm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ItemMessageBinding
import com.example.phoneforfilm.view.ChatActivity

class MessageAdapter(
    private val context: Context,
    private var messages: List<Message>
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        with(holder.binding) {
            tvMessage.text = message.text
            tvTimestamp.text = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                .format(message.timestamp)
            root.setOnLongClickListener {
                (context as? ChatActivity)?.onMessageLongPressed(message)
                true
            }
        }
    }

    override fun getItemCount(): Int = messages.size

    /** Update the list of messages and refresh the view */
    fun update(newMessages: List<Message>) {
        this.messages = newMessages
        notifyDataSetChanged()
    }
}
