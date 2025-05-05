package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityChatListBinding

class ChatListActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CONVERSATION_ID = "conversation_id"
        const val EXTRA_SENDER_ID = "sender_id"
    }

    private lateinit var binding: ActivityChatListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvChatList.adapter = ChatListAdapter { chat ->
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra(EXTRA_CONVERSATION_ID, chat.id)
                putExtra(EXTRA_SENDER_ID, chat.senderId)
            }
            startActivity(intent)
        }
    }
}
