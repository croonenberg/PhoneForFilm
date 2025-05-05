package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityChatsBinding
import com.example.phoneforfilm.presentation.view.ChatActivity
import com.example.phoneforfilm.view.adapter.ChatListAdapter

class ChatListActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CONVERSATION_ID = "conversation_id"
        const val EXTRA_SENDER_ID = "sender_id"
    }

    private lateinit var binding: ActivityChatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvChats.adapter = ChatListAdapter { chat ->
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra(EXTRA_CONVERSATION_ID, chat.id)
                putExtra(EXTRA_SENDER_ID, chat.contactId)
            }
            startActivity(intent)
        }
    }
}
