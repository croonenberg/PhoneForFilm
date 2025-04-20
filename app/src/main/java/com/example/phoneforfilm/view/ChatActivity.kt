package com.example.phoneforfilm.view

import android.os.Bundle
import android.widget.EditText
import android.app.TimePickerDialog
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.R
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.data.MessageRepository
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.viewmodel.ChatViewModelFactory
import java.util.*

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels {
        ChatViewModelFactory(
            MessageRepository(
                AppDatabase.getDatabase(this).messageDao()
            )
        )
    }

    private lateinit var adapter: MessageAdapter
    private val currentUserId: Long = 1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MessageAdapter(currentUserId) { message ->
            showMessageOptions(message)
        }

        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = this@ChatActivity.adapter
        }

        val chatId = intent.getLongExtra("CONVERSATION_ID", -1)
        viewModel.loadMessages(chatId)
        viewModel.messages.observe(this) { list ->
            adapter.submitList(list)
            if (list.isNotEmpty()) {
                binding.recyclerViewMessages.scrollToPosition(list.size - 1)
            }
        }

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotBlank()) {
                viewModel.sendMessage(chatId, text, currentUserId)
                binding.editTextMessage.text?.clear()
            }
        }
    }

    private fun showMessageOptions(message: Message) {
        val options = arrayOf(
            getString(R.string.edit_message),
            getString(R.string.edit_time),
            getString(R.string.change_status),
            getString(R.string.change_sender),
            if (message.pinned) getString(R.string.unpin) else getString(R.string.pin),
            if (message.favorite) getString(R.string.unfavorite) else getString(R.string.favorite),
            getString(R.string.delete_message),
            getString(R.string.cancel)
        )
        AlertDialog.Builder(this)
            .setItems(options) { _, which ->
                when (options[which]) {
                    getString(R.string.edit_message) -> editMessage(message)
                    getString(R.string.edit_time) -> editTime(message)
                    getString(R.string.change_status) -> changeStatus(message)
                    getString(R.string.change_sender) -> changeSender(message)
                    getString(R.string.pin) -> togglePin(message, true)
                    getString(R.string.unpin) -> togglePin(message, false)
                    getString(R.string.favorite) -> toggleFavorite(message, true)
                    getString(R.string.unfavorite) -> toggleFavorite(message, false)
                    getString(R.string.delete_message) -> deleteMessage(message)
                }
            }
            .show()
    }

    // editMessage, editTime, changeStatus, changeSender, togglePin, toggleFavorite, deleteMessage implementations...
}
