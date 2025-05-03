package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ConversationAdapter
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import com.example.phoneforfilm.viewmodel.ChatListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding
    private lateinit var adapter: ConversationAdapter
    private val viewModel by viewModels<ChatListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ConversationAdapter(emptyList()) {
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("chatId", it.id)
            startActivity(intent)
        }

        binding.rvConversations.layoutManager = LinearLayoutManager(this)
        binding.rvConversations.adapter = adapter

        viewModel.conversations.observe(this) {
            adapter.submitList(it)
        }

        
    }
}