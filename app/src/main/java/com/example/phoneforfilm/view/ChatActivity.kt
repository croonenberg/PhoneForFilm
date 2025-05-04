package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeMessages()

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                viewModel.sendMessage(text)
                binding.editTextMessage.text?.clear()
            }
        }

        // Start from intent
        viewModel.startConversation(intent.getIntExtra("conversationId", -1))
    }

    private fun setupRecyclerView() {
        adapter = MessageAdapter(this, emptyList())
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        binding.recyclerViewMessages.adapter = adapter
    }

    private fun observeMessages() {
        viewModel.messages.observe(this) { list ->
            adapter.update(list)
            binding.recyclerViewMessages.scrollToPosition(list.size - 1)
        }
    }

    /** Callback vanuit adapter */
    fun onMessageLongPressed(message: Message) {
        // TODO: open context menu (edit/copy/delete)
    }

    fun onChangeTheme() {
        // TODO: implement theme switch
    }

    fun onChangeLanguage() {
        // TODO: implement language switch
    }
}
