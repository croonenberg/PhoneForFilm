package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import com.example.phoneforfilm.presentation.view.ChatActivity

class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Voorbeeld: click handler
        binding.exampleChatItem.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra(ChatActivity.EXTRA_CONVERSATION_ID, 1)
                putExtra(ChatActivity.EXTRA_SENDER_ID, 2)
            }
            startActivity(intent)
        }
    }
}
