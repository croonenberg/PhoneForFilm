package com.example.phoneforfilm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ItemMessageBinding

class MessageAdapter(
    private val context: Context,
    private val messages: List<Message>
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
            tvTimestamp.text = message.formattedTime
            // show options on long-press (e.g. toggle sender/theme/language via ChatActivity)
            root.setOnLongClickListener {
                // delegate to activity if needed
                (context as? com.example.phoneforfilm.view.ChatActivity)
                    ?.onMessageLongPressed(message)
                true
            }
        }
    }

    override fun getItemCount() = messages.size
}
