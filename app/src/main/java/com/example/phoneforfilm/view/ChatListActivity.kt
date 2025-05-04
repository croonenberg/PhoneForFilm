package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityChatListBinding

class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding
    private val viewModel: com.example.phoneforfilm.viewmodel.ChatListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // observe conversation list
        viewModel.conversations.observe(this) { list ->
            binding.rvConversations.adapter = com.example.phoneforfilm.adapter.ConversationAdapter(this, list)
        }

        // new conversation -> pick contact
        binding.fabNewConversation.setOnClickListener {
            startActivity(Intent(this, ContactPickerActivity::class.java))
        }
    }
}
