package com.example.phoneforfilm.view

import android.os.Bundle
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

        viewModel.messages.observe(this) { list ->
            adapter.apply {
                (messages as? MutableList)?.apply {
                    clear()
                    addAll(list)
                }
                notifyDataSetChanged()
            }
        }
    }
}