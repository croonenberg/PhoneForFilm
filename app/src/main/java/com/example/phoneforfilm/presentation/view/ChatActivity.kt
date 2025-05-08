package com.example.phoneforfilm.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.presentation.adapter.MessageAdapter
import com.example.phoneforfilm.presentation.viewmodel.ChatViewModel
import com.example.phoneforfilm.settings.SettingsViewModel
import com.example.phoneforfilm.utils.ThemeManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CONVERSATION_ID = "conversation_id"
        const val EXTRA_SENDER_ID = "sender_id"
        const val EXTRA_CONTACT_NAME = "contact_name"
    }

    private lateinit var binding: ActivityChatBinding
    private val settingsViewModel: SettingsViewModel by viewModels()
    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsViewModel.currentTheme.observe(this) { themeKey ->
            ThemeManager.applyTheme(this, themeKey)
        }

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val conversationId = intent.getIntExtra(EXTRA_CONVERSATION_ID, -1)
        val senderId = intent.getIntExtra(EXTRA_SENDER_ID, -1)
        val contactName = intent.getStringExtra(EXTRA_CONTACT_NAME) ?: "Chat"

        binding.toolbarChat.title = contactName

        val msgAdapter = MessageAdapter()
        binding.rvMessages.layoutManager = LinearLayoutManager(this)
        binding.rvMessages.adapter = msgAdapter

        chatViewModel.messages.observe(this) { msgs ->
            msgAdapter.submitList(msgs)
            if (msgs.isNotEmpty()) {
                binding.rvMessages.scrollToPosition(msgs.size - 1)
            }
        }

        chatViewModel.loadMessagesForChat(conversationId)

        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString()
            if (text.isNotBlank()) {
                chatViewModel.sendMessage(text, conversationId, senderId)
                binding.etMessage.text?.clear()
            }
        }
    }
}
