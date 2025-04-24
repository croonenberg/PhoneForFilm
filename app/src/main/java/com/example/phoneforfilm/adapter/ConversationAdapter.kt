
package com.example.phoneforfilm.adapter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ItemConversationBinding

/**
 * Single‑line preview of each conversation.
 * Shows avatar (initials), contact name, last message snippet, and time.
 */
class ConversationAdapter(
    private var conversations: List<Conversation>,
    private var contactNames: Map<Int, String>,
    private val onClick: (Conversation) -> Unit
) : RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>() {

    inner class ConversationViewHolder(val binding: ItemConversationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(conversation: Conversation) {
            val name = contactNames[conversation.contactId] ?: binding.root.context.getString(R.string.unknown_contact)
            binding.tvContactNameConversation.text = name
            binding.tvLastMessage.text = conversation.lastMessage
            binding.tvTimestamp.text = DateFormat.format("HH:mm", conversation.timestamp)

            binding.ivAvatar.setImageDrawable(generateInitialsDrawable(name))

            binding.root.setOnClickListener { onClick(conversation) }
        }

        private fun generateInitialsDrawable(name: String?): BitmapDrawable {
            val initials = name
                ?.split(" ")
                ?.take(2)
                ?.joinToString("") { it.first().uppercase() }
                ?: "?"
            val size = 128
            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            paint.color = ContextCompat.getColor(binding.root.context, R.color.teal_700)
            canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)

            paint.color = ContextCompat.getColor(binding.root.context, android.R.color.white)
            paint.textSize = 48f
            paint.typeface = Typeface.DEFAULT_BOLD
            val bounds = Rect()
            paint.getTextBounds(initials, 0, initials.length, bounds)
            canvas.drawText(
                initials,
                size / 2f - bounds.width() / 2f,
                size / 2f + bounds.height() / 2f,
                paint
            )
            return BitmapDrawable(binding.root.resources, bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val binding = ItemConversationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.bind(conversations[position])
    }

    override fun getItemCount(): Int = conversations.size
}
