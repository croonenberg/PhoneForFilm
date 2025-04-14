
package com.example.phoneforfilm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.view.MessageAdapter

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageAdapter = MessageAdapter(emptyList())
        binding.recyclerViewChat.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = messageAdapter
        }

        chatViewModel.getAllMessages().observe(this) { messages ->
            messageAdapter.updateMessages(messages)
            binding.recyclerViewChat.scrollToPosition(messages.size - 1)
        }

        binding.btnSend.setOnClickListener {
            val messageText = binding.editTextMessage.text.toString()
            if (messageText.isNotBlank()) {
                val message = Message(0, messageText, System.currentTimeMillis(), true, "sent")
                chatViewModel.insertMessage(message)
                binding.editTextMessage.text.clear()
            }
        }
    }
}
