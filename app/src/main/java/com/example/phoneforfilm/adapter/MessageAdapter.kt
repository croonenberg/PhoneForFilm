package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.databinding.ItemMessageReceivedBinding
import com.example.phoneforfilm.databinding.ItemMessageSentBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MessageAdapter : ListAdapter<Message, RecyclerView.ViewHolder> {

    private val currentUserId: Long
    private val onMessageLongClick: (Message) -> Unit

    constructor(currentUserId: Long, onMessageLongClick: (Message) -> Unit) : super(
        MessageDiffCallback()
    ) {
        this.currentUserId = currentUserId
        this.onMessageLongClick = onMessageLongClick
    }

    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
    }

    override fun getItemViewType(position: Int): Int =
        if (getItem(position).senderId == currentUserId) VIEW_TYPE_SENT else VIEW_TYPE_RECEIVED

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == VIEW_TYPE_SENT) {
            SentViewHolder(
                ItemMessageSentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            ReceivedViewHolder(
                ItemMessageReceivedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = getItem(position)
        when (holder) {
            is SentViewHolder -> holder.bind(message)
        
        val pinIcon = holder.itemView.findViewById<ImageView>(R.id.pinIcon)
        pinIcon.visibility = if (message.isPinned) View.VISIBLE else View.GONE
    
        
        val statusIcon = holder.itemView.findViewById<ImageView>(R.id.statusIcon)
        when (message.status) {
            "sent" -> statusIcon.setImageResource(R.drawable.ic_status_sent)
            "delivered" -> statusIcon.setImageResource(R.drawable.ic_status_delivered)
            "read" -> statusIcon.setImageResource(R.drawable.ic_status_read)
        }
    

            holder.itemView.setOnLongClickListener {
                val popup = PopupMenu(holder.itemView.context, holder.itemView)
                popup.menuInflater.inflate(R.menu.message_options_menu, popup.menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
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
                        else -> false
                    }
                }
                popup.show()
                true
            }

            is ReceivedViewHolder -> holder.bind(message)
        
        val pinIcon = holder.itemView.findViewById<ImageView>(R.id.pinIcon)
        pinIcon.visibility = if (message.isPinned) View.VISIBLE else View.GONE
    
        
        val statusIcon = holder.itemView.findViewById<ImageView>(R.id.statusIcon)
        when (message.status) {
            "sent" -> statusIcon.setImageResource(R.drawable.ic_status_sent)
            "delivered" -> statusIcon.setImageResource(R.drawable.ic_status_delivered)
            "read" -> statusIcon.setImageResource(R.drawable.ic_status_read)
        }
    

            holder.itemView.setOnLongClickListener {
                val popup = PopupMenu(holder.itemView.context, holder.itemView)
                popup.menuInflater.inflate(R.menu.message_options_menu, popup.menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
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
                        else -> false
                    }
                }
                popup.show()
                true
            }

        }
    }

    inner class SentViewHolder(private val binding: ItemMessageSentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.tvSentMessage.text = message.text
            binding.tvSentTime.text =
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(message.timestamp))
            when (message.status) {
                0 -> binding.ivStatus.setImageResource(R.drawable.ic_status_sent)
                1 -> binding.ivStatus.setImageResource(R.drawable.ic_status_delivered)
                2 -> binding.ivStatus.setImageResource(R.drawable.ic_status_read)
            }
            binding.ivPin.visibility = if (message.pinned) View.VISIBLE else View.GONE
            binding.ivFavorite.visibility = if (message.favorite) View.VISIBLE else View.GONE
            binding.root.setOnLongClickListener {
                onMessageLongClick(message)
                true
            }
        }
    }

    inner class ReceivedViewHolder(private val binding: ItemMessageReceivedBinding) :
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

    class MessageDiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean =
            oldItem == newItem
    }
}
