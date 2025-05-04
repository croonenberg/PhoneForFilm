package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.data.model.Message

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: com.example.phoneforfilm.viewmodel.ChatViewModel by viewModels()
    private var conversationId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        conversationId = intent.getIntExtra("conversationId", -1)
        binding.recyclerViewMessages.adapter = com.example.phoneforfilm.adapter.MessageAdapter(this, emptyList())

        // observe messages
        viewModel.getMessages(conversationId).observe(this) { list ->
            (binding.recyclerViewMessages.adapter as com.example.phoneforfilm.adapter.MessageAdapter).apply {
                // update adapter's data list (if you implement update method)
            }
        }

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotBlank()) {
                viewModel.sendMessage(Message(0, conversationId, text, System.currentTimeMillis(), true))
                binding.editTextMessage.text.clear()
            }
        }
    }

    fun onMessageLongPressed(msg: Message) {
        // TODO: show options (edit/delete/theme/lang)
    }
}
