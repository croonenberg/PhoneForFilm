
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

/**
 * RecyclerView‑adapter met long‑click‑menu, status‑vinkjes en themakleuren.
 * De adapter krijgt nu het huidige userId binnen zodat sent/received
 * correct bepaald wordt.
 */
class MessageAdapter(
    private val currentUserId: Long
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
    }

    var messages: List<Message> = emptyList()

    // Callbacks voor message‑acties
    var onMessageEdit: ((Message) -> Unit)? = null
    var onMessageTimeChange: ((Message) -> Unit)? = null
    var onMessageStatusChange: ((Message) -> Unit)? = null
    var onMessageDelete: ((Message) -> Unit)? = null
    var onMessagePinToggle: ((Message) -> Unit)? = null
    var onMessageFavoriteToggle: ((Message) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].senderId == currentUserId) VIEW_TYPE_SENT
        else VIEW_TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SENT) {
            val binding = ItemMessageSentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            SentViewHolder(binding)
        } else {
            val binding = ItemMessageReceivedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
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
            binding.tvSentMessage.text = if (message.isDeleted)
                binding.root.context.getString(R.string.message_deleted)
            else message.text

            // Tijd + status‑vinkje
            binding.tvSentTime.text = DateFormat.format("HH:mm", message.timestamp).toString()
            val iconRes = when (message.status) {
                0 -> R.drawable.ic_status_sent
                1 -> R.drawable.ic_status_delivered
                2 -> R.drawable.ic_status_read
                else -> R.drawable.ic_status_sent
            }
            binding.tvSentTime.setCompoundDrawablesWithIntrinsicBounds(0, 0, iconRes, 0)

            showOptionsMenuIfLongPressed(binding.root, message)
        }
    }

    inner class ReceivedViewHolder(private val binding: ItemMessageReceivedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.tvReceivedMessage.text = if (message.isDeleted)
                binding.root.context.getString(R.string.message_deleted)
            else message.text

            binding.tvReceivedTime.text =
                DateFormat.format("HH:mm", message.timestamp).toString()

            // Optioneel: ook received‑bubbles een menu geven
            showOptionsMenuIfLongPressed(binding.root, message)
        }
    }

    private fun showOptionsMenuIfLongPressed(view: android.view.View, message: Message) {
        view.setOnLongClickListener {
            PopupMenu(view.context, view).apply {
                inflate(R.menu.message_options_menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_edit_message     -> onMessageEdit?.invoke(message)
                        R.id.menu_change_time      -> onMessageTimeChange?.invoke(message)
                        R.id.menu_change_status    -> onMessageStatusChange?.invoke(message)
                        R.id.menu_toggle_pin       -> onMessagePinToggle?.invoke(message)
                        R.id.menu_toggle_favorite  -> onMessageFavoriteToggle?.invoke(message)
                        R.id.menu_delete           -> onMessageDelete?.invoke(message)
                    }
                    true
                }
            }.show()
            true
        }
    }
}
