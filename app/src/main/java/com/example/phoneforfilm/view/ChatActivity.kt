package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.data.Message
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private lateinit var adapter: MessageAdapter
    private var chatId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatId = intent.getIntExtra("chatId", 0)

        adapter = MessageAdapter()
        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(context).apply { stackFromEnd = true }
            adapter = this@ChatActivity.adapter
        }

        viewModel.messages.observe(this) { msgs ->
            adapter.submitList(msgs)
            binding.recyclerViewMessages.scrollToPosition(msgs.size - 1)
        }

        viewModel.loadMessages(chatId)

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                val message = Message(
                    chatId = chatId,
                    senderId = 0, // 0 = me
                    text = text,
                    timestamp = System.currentTimeMillis(),
                    status = 0
                )
                viewModel.sendMessage(message)
                binding.editTextMessage.text.clear()
            }
        }
    }
}
