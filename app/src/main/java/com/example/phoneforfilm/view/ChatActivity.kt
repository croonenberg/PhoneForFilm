package com.example.phoneforfilm.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private var conversationId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        conversationId = intent.getIntExtra("conversationId", -1)
        viewModel.loadMessages(conversationId)
    }

    /** Delete this message from DB and refresh list. */
    fun onDeleteMessage(msg: Message) {
        viewModel.deleteMessage(msg)
        viewModel.loadMessages(conversationId)
    }

    /** Toggle sender/receiver role of this message. */
    fun onToggleSender(msg: Message) {
        msg.isSender = !msg.isSender
        viewModel.updateMessage(msg)
        viewModel.loadMessages(conversationId)
    }

    // ... other methods unchanged
}
