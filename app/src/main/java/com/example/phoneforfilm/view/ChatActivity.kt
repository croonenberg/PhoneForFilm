package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private var conversationId: Int = -1
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        conversationId = intent.getIntExtra("chatId", -1)
        adapter = MessageAdapter(this, emptyList())

        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMessages.adapter = adapter

        viewModel.getMessages(conversationId).observe(this) { list ->
            adapter.update(list)
        }

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotBlank()) {
                viewModel.sendMessage(Message(conversationId = conversationId, text = text, timestamp = System.currentTimeMillis(), isSender = true))
                binding.editTextMessage.text.clear()
            }
        }
    }

    fun onMessageLongPressed(msg: Message) {
        // TODO: show theme/language/edit/delete options
    }
}
