package com.example.phoneforfilm.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.presentation.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MessageAdapter(emptyList(), this)
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMessages.adapter = adapter

        val chatId = intent.getIntExtra("chatId", -1)
        viewModel.loadMessagesForChat(chatId)

        viewModel.messages.observe(this) { messages ->
            adapter.updateMessages(messages)
            binding.recyclerViewMessages.scrollToPosition(messages.lastIndex)
        }

        binding.buttonSend.setOnClickListener {
            val messageText = binding.editTextMessage.text.toString().trim()
            if (messageText.isNotBlank()) {
                viewModel.sendMessage(messageText, chatId)
                binding.editTextMessage.text.clear()
            }
        }
    }
}
