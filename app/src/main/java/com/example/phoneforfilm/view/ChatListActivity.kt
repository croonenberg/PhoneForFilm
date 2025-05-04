package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import com.example.phoneforfilm.viewmodel.ChatListViewModel

class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding
    private val viewModel: ChatListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerConversations.adapter = ConversationAdapter(
            this,
            viewModel.conversations.value ?: emptyList()
        )

        binding.fabNewChat.setOnClickListener {
            viewModel.createFor(binding) // fixed call
        }

        viewModel.conversations.observe(this) { list ->
            (binding.recyclerConversations.adapter as ConversationAdapter).update(list)
        }
    }
}
