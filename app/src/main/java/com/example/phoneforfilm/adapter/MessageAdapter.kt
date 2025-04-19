package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.databinding.ItemMessageReceivedBinding
import com.example.phoneforfilm.databinding.ItemMessageSentBinding
import java.text.SimpleDateFormat
import java.util.*

class MessageAdapter(
    private val currentUserId: Long,
    private val onMessageLongClick: (Message) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val messages = mutableListOf<Message>()

    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
    }

    inner class SentViewHolder(val binding: ItemMessageSentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.tvSentMessage.text = message.text
            binding.tvSentTime.text =
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(message.timestamp))
            // status icons logic
            when (message.status) {
                0 -> binding.ivStatus.setImageResource(com.example.phoneforfilm.R.drawable.ic_status_sent)
                1 -> binding.ivStatus.setImageResource(com.example.phoneforfilm.R.drawable.ic_status_delivered)
                2 -> binding.ivStatus.setImageResource(com.example.phoneforfilm.R.drawable.ic_status_read)
            }
            // pin/favorite visibility
            binding.ivPin.visibility = if (message.pinned) View.VISIBLE else View.GONE
            binding.ivFavorite.visibility = if (message.favorite) View.VISIBLE else View.GONE

            binding.root.setOnLongClickListener {
                onMessageLongClick(message)
                true
            }
        }
    }

    inner class ReceivedViewHolder(val binding: ItemMessageReceivedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.tvReceivedMessage.text = message.text
            binding.tvReceivedTime.text =
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(message.timestamp))
            binding.ivPin.visibility = if (message.pinned) View.VISIBLE else View.GONE
            binding.ivFavorite.visibility = if (message.favorite) View.VISIBLE else View.GONE

            binding.root.setOnLongClickListener {
                onMessageLongClick(message)
                true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].senderId == currentUserId) VIEW_TYPE_SENT else VIEW_TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == VIEW_TYPE_SENT) {
            SentViewHolder(
                ItemMessageSentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        } else {
            ReceivedViewHolder(
                ItemMessageReceivedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = messages[position]
        if (holder is SentViewHolder) holder.bind(msg)
        else if (holder is ReceivedViewHolder) holder.bind(msg)
    }

    override fun getItemCount(): Int = messages.size

    fun submitList(list: List<Message>) {
        messages.clear()
        messages.addAll(
            list.sortedWith(
                compareByDescending<Message> { it.pinned }
                    .thenBy { it.timestamp }
            )
        )
        notifyDataSetChanged()
    }
}
