package com.example.phoneforfilm.adapter

import com.example.phoneforfilm.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.databinding.ItemMessageSentBinding

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    var messages: List<Message> = emptyList()

    var onMessageEdit: ((Message) -> Unit)? = null
    var onMessageTimeChange: ((Message) -> Unit)? = null
    var onMessageStatusChange: ((Message) -> Unit)? = null
    var onMessageDelete: ((Message) -> Unit)? = null
    var onMessagePinToggle: ((Message) -> Unit)? = null

    inner class MessageViewHolder(val binding: ItemMessageSentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {

            if (message.isDeleted) {
                binding.tvMessage.text = binding.root.context.getString(R.string.message_deleted)
            } else {
                binding.tvMessage.text = message.text
            }

            // Pin-icoon tonen
            binding.pinIcon.visibility = if (message.pinned) View.VISIBLE else View.GONE

            binding.favoriteIcon.visibility = if (message.favorite) View.VISIBLE else View.GONE

            binding.root.setOnLongClickListener {
                val popup = PopupMenu(binding.root.context, binding.root)
                popup.menuInflater.inflate(R.menu.message_options_menu, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_edit -> {
                            onMessageEdit?.invoke(message)
                            true
                        }
                        R.id.menu_change_time -> {
                            onMessageTimeChange?.invoke(message)
                            true
                        }
                        R.id.menu_change_status -> {
                            onMessageStatusChange?.invoke(message)
                            true
                        }
                        R.id.menu_delete -> {
                            onMessageDelete?.invoke(message)
                            true
                        }
                        R.id.menu_pin -> {
                            onMessagePinToggle?.invoke(message)
                            true
                        }
                        else -> false
                    }
                }
                popup.show()
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageSentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size
}
