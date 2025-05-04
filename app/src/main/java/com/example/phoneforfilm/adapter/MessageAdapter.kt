package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ItemMessageBinding
import com.example.phoneforfilm.view.ChatActivity
import android.widget.PopupMenu

class MessageAdapter(var messages: MutableList<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(private val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener { view ->
                PopupMenu(view.context, view).apply {
                    inflate(R.menu.menu_message_full)
                    setOnMenuItemClickListener { item ->
                        val msg = messages[adapterPosition]
                        val activity = view.context as? ChatActivity
                        when (item.itemId) {
                            R.id.action_edit -> activity?.onEditMessage(msg)
                            R.id.action_delete -> activity?.onDeleteMessage(msg)
                            R.id.action_copy -> activity?.onCopyMessage(msg)
                            R.id.action_change_theme -> activity?.onChangeTheme()
                            R.id.action_change_language -> activity?.onChangeLanguage()
                            R.id.action_toggle_sender -> activity?.onToggleSender(msg)
                        }
                        true
                    }
                    show()
                }
                true
            }
        }

        fun bind(message: Message) {
            // dynamic background and text color
            val context = binding.root.context
            if (message.isSender) {
                binding.tvMessage.setBackgroundResource(R.drawable.chat_bubble_sent)
                binding.tvMessage.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            } else {
                binding.tvMessage.setBackgroundResource(R.drawable.chat_bubble_received)
                binding.tvMessage.setTextColor(ContextCompat.getColor(context, android.R.color.black))
            }
            binding.tvTimestamp.text = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                .format(message.timestamp)
            // adjust margins
            val params = binding.messageContainer.layoutParams as ViewGroup.MarginLayoutParams
            if (message.isSender) {
                params.marginStart = 50
                params.marginEnd = 0
            } else {
                params.marginStart = 0
                params.marginEnd = 50
            }
            binding.messageContainer.layoutParams = params
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.size

    fun updateData(newMessages: List<Message>) {
        messages.clear()
        messages.addAll(newMessages)
        notifyDataSetChanged()
    }
}
