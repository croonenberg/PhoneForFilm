package com.example.phoneforfilm.adapter

import android.text.format.DateFormat
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.databinding.ItemMessageReceivedBinding
import com.example.phoneforfilm.databinding.ItemMessageSentBinding

class MessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
    }

    var messages: List<Message> = emptyList()

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].senderId == /* your user id or condition */ 0L) VIEW_TYPE_SENT
               else VIEW_TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SENT) {
            val binding = ItemMessageSentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SentViewHolder(binding)
        } else {
            val binding = ItemMessageReceivedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ReceivedViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        if (holder is SentViewHolder) holder.bind(message)
        else if (holder is ReceivedViewHolder) holder.bind(message)
    }

    override fun getItemCount(): Int = messages.size

    inner class SentViewHolder(private val binding: ItemMessageSentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.tvMessage.text = if (message.isDeleted)
                binding.root.context.getString(R.string.message_deleted) else message.text

            // timestamp
            binding.tvSentTime.text = DateFormat.format("HH:mm", message.timestamp).toString()

            // status icon
            binding.statusIcon.setImageResource(
                when (message.status) {
                    0 -> R.drawable.ic_status_sent
                    1 -> R.drawable.ic_status_delivered
                    2 -> R.drawable.ic_status_read
                    else -> R.drawable.ic_status_sent
                }
            )

            // dynamic text color on bubble
            val typedValue = TypedValue()
            val colorAttr = if (message.status >= 0) android.R.attr.colorOnPrimary
                            else android.R.attr.colorOnPrimary
            binding.tvMessage.setTextColor(
                TypedValue()
                    .apply { binding.root.context.theme.resolveAttribute(colorAttr, this, true) }
                    .data
            )
        }
    }

    inner class ReceivedViewHolder(private val binding: ItemMessageReceivedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.tvReceivedMessage.text = if (message.isDeleted)
                binding.root.context.getString(R.string.message_deleted) else message.text

            binding.tvReceivedTime.text = DateFormat.format("HH:mm", message.timestamp).toString()
        }
    }
}
