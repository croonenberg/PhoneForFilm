
package com.example.phoneforfilm.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.databinding.ItemMessageReceivedBinding
import com.example.phoneforfilm.databinding.ItemMessageSentBinding

class MessageAdapter(
    private val currentUserId: Long
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
    }

    var messages: List<Message> = emptyList()

    var onMessageEdit: ((Message) -> Unit)? = null
    var onMessageTimeChange: ((Message) -> Unit)? = null
    var onMessageStatusChange: ((Message) -> Unit)? = null
    var onToggleDirection: ((Message) -> Unit)? = null
    var onMessageDelete: ((Message) -> Unit)? = null

    override fun getItemViewType(position: Int): Int =
        if (messages[position].senderId == currentUserId) VIEW_TYPE_SENT else VIEW_TYPE_RECEIVED

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == VIEW_TYPE_SENT) {
            SentViewHolder(
                ItemMessageSentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            ReceivedViewHolder(
                ItemMessageReceivedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = messages[position]
        if (holder is SentViewHolder) holder.bind(msg) else (holder as ReceivedViewHolder).bind(msg)
    }

    override fun getItemCount(): Int = messages.size

    private fun showMenu(anchor: android.view.View, msg: Message) {
        PopupMenu(anchor.context, anchor).apply {
            inflate(R.menu.message_options_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_edit_message      -> onMessageEdit?.invoke(msg)
                    R.id.menu_change_time       -> onMessageTimeChange?.invoke(msg)
                    R.id.menu_change_status     -> onMessageStatusChange?.invoke(msg)
                    R.id.menu_toggle_direction  -> onToggleDirection?.invoke(msg)
                    R.id.menu_delete            -> onMessageDelete?.invoke(msg)
                }
                true
            }
        }.show()
    }

    inner class SentViewHolder(private val b: ItemMessageSentBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(msg: Message) {
            b.tvSentMessage.text = if (msg.isDeleted) b.root.context.getString(R.string.message_deleted) else msg.text
            b.tvSentTime.text = DateFormat.format("HH:mm", msg.timestamp)
            val statusIcon = when (msg.status) {
                0 -> R.drawable.ic_status_sent
                1 -> R.drawable.ic_status_delivered
                2 -> R.drawable.ic_status_read
                else -> R.drawable.ic_status_sent
            }
            b.tvSentTime.setCompoundDrawablesWithIntrinsicBounds(0, 0, statusIcon, 0)
            b.root.setOnLongClickListener { showMenu(it, msg); true }
        }
    }

    inner class ReceivedViewHolder(private val b: ItemMessageReceivedBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(msg: Message) {
            b.tvReceivedMessage.text = if (msg.isDeleted) b.root.context.getString(R.string.message_deleted) else msg.text
            b.tvReceivedTime.text = DateFormat.format("HH:mm", msg.timestamp)
            b.root.setOnLongClickListener { showMenu(it, msg); true }
        }
    }
}
