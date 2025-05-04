package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.view.adapter.MessageAdapter
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

        adapter = MessageAdapter(
            onEditMessage = { msg -> viewModel.updateMessage(msg) },
            onCopyMessage = { msg -> /* copy to clipboard */ },
            onChangeTheme = { viewModel.onChangeTheme() },
            onChangeLanguage = { viewModel.onChangeLanguage() },
            onMessageLongPressed = { msg -> /* show options */ }
        )

        binding.rvMessages.layoutManager = LinearLayoutManager(this)
        binding.rvMessages.adapter = adapter

        viewModel.messages.observe(this) { list ->
            adapter.submitList(list)
            binding.rvMessages.scrollToPosition(list.size - 1)
        }

        // start conversation based on passed ID
        viewModel.startConversation(intent.getIntExtra("conversationId", -1))

        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString()
            if (text.isNotBlank()) {
                viewModel.sendMessage(text)
                binding.etMessage.text?.clear()
            }
        }
    }

    /** Live theme chooser for this chat. */
    fun onChangeTheme() {
        // TODO: show theme chooser
    }

    /** Live language chooser for this chat. */
    fun onChangeLanguage() {
        // TODO: show language chooser
    }

    /** Toggle sender/receiver role of this message. */
    fun onToggleSender(msg: Message) {
        msg.isSender = !msg.isSender
        viewModel.updateMessage(msg)
    }
}
