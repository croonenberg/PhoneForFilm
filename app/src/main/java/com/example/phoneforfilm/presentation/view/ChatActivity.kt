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

    private lateinit var binding: ActivityChatBinding
    private val chatViewModel: ChatViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsViewModel.currentTheme.observe(this) { themeKey ->
            ThemeManager.applyTheme(this, themeKey)
        }

        val chatId = intent.getIntExtra("conversation_id", -1)
        val senderId = intent.getIntExtra("sender_id", -1)

        val adapter = MessageAdapter()
        binding.rvMessages.layoutManager = LinearLayoutManager(this)
        binding.rvMessages.adapter = adapter

        chatViewModel.messages.observe(this) { msgs ->
            adapter.submitList(msgs)
            if (msgs.isNotEmpty()) {
                binding.rvMessages.scrollToPosition(msgs.size - 1)
            }
        }

        chatViewModel.loadMessagesForChat(chatId)

        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString()
            if (text.isNotBlank()) {
                chatViewModel.sendMessage(text, chatId, senderId)
                binding.etMessage.text?.clear()
            }
        }
    }
}
