package com.example.phoneforfilm.ui.chat

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Early check: if conversation id missing, close gracefully
        if (!intent.hasExtra(EXTRA_CONVERSATION_ID)) {
            Toast.makeText(this, "No conversation selected", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeMessages()
    }

    private fun observeMessages() {
        // existing implementation
    }

    companion object {
        const val EXTRA_CONVERSATION_ID = "conversationId"
    }
}
