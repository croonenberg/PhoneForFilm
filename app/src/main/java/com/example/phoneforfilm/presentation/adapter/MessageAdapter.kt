package com.example.phoneforfilm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ItemMessageReceivedBinding
import com.example.phoneforfilm.databinding.ItemMessageSentBinding
import com.example.phoneforfilm.presentation.view.ChatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MessageAdapter(
    private var messages: List<Message>,
    private val context: ChatActivity
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        if (messages[position].isSender) VIEW_TYPE_SENT else VIEW_TYPE_RECEIVED

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if (viewType == VIEW_TYPE_SENT) {
            val binding = ItemMessageSentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageViewHolder(binding)
        } else {
            val binding = ItemMessageReceivedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
        holder.itemView.setOnLongClickListener {
            context.onMessageLongPressed(message)
            true
        }
    }

    override fun getItemCount(): Int = messages.size

    fun updateMessages(newMessages: List<Message>) {
        messages = newMessages
        notifyDataSetChanged()
    }

    inner class MessageViewHolder(private val binding: Any) :
        RecyclerView.ViewHolder(
            (binding as? ItemMessageSentBinding)?.root
                ?: (binding as ItemMessageReceivedBinding).root
        ) {

        fun bind(message: Message) {
            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(message.timestamp))
            when (binding) {
                is ItemMessageSentBinding -> {
                    binding.tvSentMessage.text = message.text
                    binding.tvSentTime.text = time
                }
                is ItemMessageReceivedBinding -> {
                    binding.tvReceivedMessage.text = message.text
                    binding.tvReceivedTime.text = time
                }
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 0
    }
}

