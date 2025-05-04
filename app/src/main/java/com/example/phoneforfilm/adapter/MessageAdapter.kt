package com.example.phoneforfilm.adapter

import android.widget.Toast
import android.widget.PopupMenu
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ItemMessageBinding

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
                        when (item.itemId) {
                            R.id.action_edit -> {
                                Toast.makeText(view.context, "Edit: ${msg.text}", Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.action_delete -> {
                                Toast.makeText(view.context, "Delete: ${msg.text}", Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.action_copy -> {
                                Toast.makeText(view.context, "Copied: ${msg.text}", Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.action_change_theme -> {
                                Toast.makeText(view.context, "Change Theme for this chat", Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.action_change_language -> {
                                Toast.makeText(view.context, "Change Language", Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.action_toggle_sender -> {
                                Toast.makeText(view.context, "Toggle Sender/Receiver", Toast.LENGTH_SHORT).show()
                                true
                            }
                            else -> false
                        }
                    }
                    show()
                }
                true
            }
        }

        fun bind(message: Message) {
            binding.tvMessage.text = message.text
            binding.tvTimestamp.text = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                .format(message.timestamp)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding =
            ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
