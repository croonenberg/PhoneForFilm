package com.example.phoneforfilm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ItemMessageBinding
import com.example.phoneforfilm.view.ChatActivity

class MessageAdapter(private val context: Context, private val messages: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        with(holder.binding) {
            tvMessage.text = message.text
            tvTimestamp.text = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                .format(message.timestamp)
            // call ChatActivity methods via context
            root.setOnLongClickListener {
                val activity = context as ChatActivity
                activity.onEditMessage(message)
                true
            }
            btnCopy.setOnClickListener {
                (context as ChatActivity).onCopyMessage(message)
            }
            btnTheme.setOnClickListener {
                (context as ChatActivity).onChangeTheme()
            }
            btnLanguage.setOnClickListener {
                (context as ChatActivity).onChangeLanguage()
            }
        }
    }

    override fun getItemCount() = messages.size
}
