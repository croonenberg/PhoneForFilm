
package com.example.phoneforfilm.view

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.R
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.MessageRepository
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.utils.ThemeManager
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.viewmodel.ChatViewModelFactory
import java.util.Calendar

/** Chat‑scherm met thema‑ondersteuning en long‑click menu. */
class ChatActivity : BaseActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels {
        ChatViewModelFactory(
            MessageRepository(AppDatabase.getDatabase(this).messageDao())
        )
    }

    private val currentUserId: Long = 1L
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MessageAdapter(currentUserId).apply {
            onMessageEdit         = { message -> editMessage(message) }
            onMessageTimeChange   = { message -> editTime(message) }
            onMessageStatusChange = { message -> changeStatus(message) }
            onMessageDelete       = { message -> deleteMessage(message) }
            onMessagePinToggle    = { message -> togglePin(message, !message.pinned) }
        }

        binding.recyclerViewMessages.layoutManager =
            LinearLayoutManager(this@ChatActivity)
        binding.recyclerViewMessages.adapter = adapter

        val chatId = intent.getLongExtra("CONVERSATION_ID", -1L)
        viewModel.loadMessages(chatId)
        viewModel.messages.observe(this) { list ->
            adapter.messages = list
            adapter.notifyDataSetChanged()
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

    // ----- helpers (identiek aan origineel) -----
    private fun editMessage(message: com.example.phoneforfilm.data.Message) {
        val input = EditText(this).apply {
            setText(message.text)
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
        }
        AlertDialog.Builder(this)
            .setTitle(R.string.edit_message)
            .setView(input)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val updated = message.copy(text = input.text.toString())
                viewModel.updateMessage(updated)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun editTime(message: com.example.phoneforfilm.data.Message) {
        val cal = Calendar.getInstance().apply { timeInMillis = message.timestamp }
        TimePickerDialog(
            this,
            { _, h, m ->
                val newTime = cal.apply {
                    set(Calendar.HOUR_OF_DAY, h)
                    set(Calendar.MINUTE, m)
                }.timeInMillis
                viewModel.updateMessage(message.copy(timestamp = newTime))
            },
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun changeStatus(message: com.example.phoneforfilm.data.Message) {
        val statuses = arrayOf(
            getString(R.string.status_sent),
            getString(R.string.status_delivered),
            getString(R.string.status_read)
        )
        AlertDialog.Builder(this)
            .setTitle(R.string.change_status)
            .setItems(statuses) { _, which ->
                viewModel.updateMessage(message.copy(status = which))
            }
            .show()
    }

    private fun deleteMessage(message: com.example.phoneforfilm.data.Message) {
        viewModel.updateMessage(message.copy(isDeleted = true))
    }

    private fun togglePin(message: com.example.phoneforfilm.data.Message, pin: Boolean) {
        viewModel.updateMessage(message.copy(pinned = pin))
    }
}
