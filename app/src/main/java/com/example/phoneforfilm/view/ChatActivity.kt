package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.presentation.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter
    private val chatId: Int by lazy { intent.getIntExtra("chat_id", -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MessageAdapter(emptyList(), this)
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMessages.adapter = adapter

        lifecycleScope.launch {
            chatViewModel.getMessagesByChatId(chatId).collectLatest { messages ->
                adapter.updateMessages(messages)
                binding.recyclerViewMessages.scrollToPosition(messages.size - 1)
            }
        }

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotBlank()) {
                chatViewModel.sendMessage(chatId = chatId, text = text)
                binding.editTextMessage.text.clear()
            }
        }
    }

    fun onMessageLongPressed(message: com.example.phoneforfilm.data.model.Message) {
        chatViewModel.deleteMessage(message)
    }
}