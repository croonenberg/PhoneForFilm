package com.example.phoneforfilm.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel by viewModels<ChatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatId = intent.getIntExtra("chatId", -1)
        viewModel.loadMessages(chatId)

        val adapter = MessageAdapter(mutableListOf())
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMessages.adapter = adapter

        viewModel.messages.observe(this) {
            adapter.updateData(it)
            binding.recyclerViewMessages.scrollToPosition(it.size - 1)
        }

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                val msg = Message(conversationId = chatId, text = text, timestamp = System.currentTimeMillis())
                viewModel.sendMessage(msg)
                binding.editTextMessage.text?.clear()
            }
        }
    }

    // Stub methods for context menu actions
    fun onEditMessage(message: Message) {
        Toast.makeText(this, "Edit: ${message.text}", Toast.LENGTH_SHORT).show()
    }

    fun onDeleteMessage(message: Message) {
        Toast.makeText(this, "Delete: ${message.text}", Toast.LENGTH_SHORT).show()
    }

    fun onCopyMessage(message: Message) {
        Toast.makeText(this, "Copied: ${message.text}", Toast.LENGTH_SHORT).show()
    }

    fun onChangeTheme() {
        Toast.makeText(this, "Theme change invoked", Toast.LENGTH_SHORT).show()
    }

    fun onChangeLanguage() {
        Toast.makeText(this, "Language change invoked", Toast.LENGTH_SHORT).show()
    }

    fun onToggleSender() {
        Toast.makeText(this, "Toggle sender/receiver", Toast.LENGTH_SHORT).show()
    }
}
