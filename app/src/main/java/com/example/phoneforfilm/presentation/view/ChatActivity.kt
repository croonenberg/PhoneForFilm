package com.example.phoneforfilm.presentation.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.presentation.adapter.MessageAdapter
import com.example.phoneforfilm.presentation.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatId = intent.getIntExtra("chatId", -1)
        if (chatId == -1) {
            Toast.makeText(this, "Chat ID is missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel.loadMessagesForChat(chatId)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = MessageAdapter(emptyList(), this)
        binding.recyclerViewMessages.adapter = adapter

        viewModel.getMessages(chatId).observe(this) { messages ->
            adapter.updateMessages(messages)
            binding.recyclerViewMessages.scrollToPosition(messages.size - 1)
        }


        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                viewModel.sendMessage(text, chatId)
                binding.editTextMessage.text.clear()
            }
        }
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