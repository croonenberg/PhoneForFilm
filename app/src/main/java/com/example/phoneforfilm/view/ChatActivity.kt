
package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.MessageRepository
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.viewmodel.ChatViewModelFactory

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels {
        ChatViewModelFactory(MessageRepository(AppDatabase.getDatabase(this).messageDao()))
    }
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageAdapter = MessageAdapter()
        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = messageAdapter
        }

        val chatId = intent.getIntExtra("CHAT_ID", -1)
        viewModel.messages.observe(this) { messages ->
            messageAdapter.submitList(messages)
        }
        
        viewModel.loadMessages(chatId)
        
        binding.buttonSend.setOnClickListener {
            val messageText = binding.editTextMessage.text.toString()
            viewModel.sendMessage(chatId, messageText)
            binding.editTextMessage.text.clear()
        }
    }
}
