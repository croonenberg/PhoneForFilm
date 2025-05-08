package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityChatsBinding
import com.example.phoneforfilm.presentation.view.ChatActivity
import com.example.phoneforfilm.ui.contact.ContactPickerActivity
import com.example.phoneforfilm.view.adapter.ChatListAdapter
import com.example.phoneforfilm.viewmodel.ChatListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CONVERSATION_ID = "conversation_id"
        const val EXTRA_SENDER_ID = "sender_id"
    }

    private lateinit var binding: ActivityChatsBinding
    private val viewModel: ChatListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ChatListAdapter { chat ->
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra(EXTRA_CONVERSATION_ID, chat.id)
                putExtra(EXTRA_SENDER_ID, chat.contactId)
            }
            startActivity(intent)
        }

        binding.rvChats.layoutManager = LinearLayoutManager(this)
        binding.rvChats.adapter = adapter

        viewModel.conversations.observe(this) { list ->
            adapter.submitList(list)
        }

        binding.fabAddChat.setOnClickListener {
            startActivity(Intent(this, ContactPickerActivity::class.java))
        }
    }
}
