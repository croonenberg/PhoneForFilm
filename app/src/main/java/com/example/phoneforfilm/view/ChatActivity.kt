package com.example.phoneforfilm.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // bestaande setup voor ChatActivity
    }

    fun onMessageLongPressed(msg: Message) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message_options))
            .setItems(arrayOf(getString(R.string.copy_message), getString(R.string.delete_message))) { _, which ->
                when (which) {
                    0 -> {
                        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("message", msg.text)
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(this, getString(R.string.copied_to_clipboard), Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        viewModel.deleteMessage(msg)
                        Toast.makeText(this, getString(R.string.message_deleted), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .show()
    }
}