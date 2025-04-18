package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.MessageAdapter
import com.example.phoneforfilm.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MessageAdapter()
        binding.recyclerViewChat.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewChat.adapter = adapter

        binding.btnSend.setOnClickListener {
            val message = binding.editTextMessage.text.toString()
            if (message.isNotBlank()) {
                viewModel.sendMessage(message)
                binding.editTextMessage.text.clear()
            }
        }

        viewModel.messages.observe(this) {
            adapter.submitList(it)
            binding.recyclerViewChat.scrollToPosition(it.size - 1)
        }
    }
}
