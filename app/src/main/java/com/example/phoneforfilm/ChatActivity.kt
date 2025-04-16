package com.example.phoneforfilm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.view.MessageAdapter

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var viewModel: ChatViewModel
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MessageAdapter(emptyList())
        binding.recyclerViewChat.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewChat.adapter = adapter

        viewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        viewModel.allMessages.observe(this) { messages ->
            adapter.updateMessages(messages)
        }

        binding.btnSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotBlank()) {
                val message = Message(
                    id = 0,
                    contactId = 1, // Correct: Int, geen String
                    timestamp = System.currentTimeMillis(),
                    text = text,
                    isSent = true,
                    status = buildString {
                        append("sent")
                    }
                )
                viewModel.insertMessage(message)
                binding.editTextMessage.text.clear()
            }
        }
    }
}
