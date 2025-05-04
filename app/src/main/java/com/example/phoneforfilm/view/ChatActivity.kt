package com.example.phoneforfilm.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // existing setup...
    }

    /** Open the edit UI for this message. */
    fun onEditMessage(msg: Message) {
        // TODO: show edit dialog or start EditConversationActivity
    }

    /** Delete this message from DB and refresh list. */
    fun onDeleteMessage(msg: Message) {
        // TODO: viewModel.deleteMessage(msg); viewModel.refreshMessages()
    }

    /** Copy message text to clipboard. */
    fun onCopyMessage(msg: Message) {
        val cb = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        cb.setPrimaryClip(ClipData.newPlainText("message", msg.text))
        Toast.makeText(this, getString(R.string.copied_to_clipboard), Toast.LENGTH_SHORT).show()
    }

    /** Live theme chooser for this chat. */
    fun onChangeTheme() {
        // TODO: show theme chooser
    }

    /** Live language chooser for this chat. */
    fun onChangeLanguage() {
        // TODO: show language chooser
    }

    /** Toggle sender/receiver role of this message. */
    fun onToggleSender(msg: Message) {
        msg.isSender = !msg.isSender
        // TODO: viewModel.updateMessage(msg)
    }
}
