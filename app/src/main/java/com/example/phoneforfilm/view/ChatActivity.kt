package com.example.phoneforfilm.view

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.phoneforfilm.view.BaseActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.R
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.MessageRepository
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.viewmodel.ChatViewModelFactory
import java.util.Calendar

class ChatActivity : BaseActivity() {
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
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MessageAdapter()

        adapter.onMessageEdit         = { message -> editMessage(message) }
        adapter.onMessageTimeChange   = { message -> editTime(message) }
        adapter.onMessageStatusChange = { message -> changeStatus(message) }
        adapter.onMessageDelete       = { message -> deleteMessage(message) }
        adapter.onMessagePinToggle    = { message ->
            // Toggle pin-status using 'pinned' property
            togglePin(message, !message.pinned)
        }

        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = this@ChatActivity.adapter
        }

        val chatId = intent.getLongExtra("CONVERSATION_ID", -1L)
        viewModel.loadMessages(chatId)
        viewModel.messages.observe(this) { list ->
            adapter.messages = list
        adapter.notifyItemInserted(adapter.itemCount - 1)
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
            { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                val updated = message.copy(timestamp = cal.timeInMillis)
                viewModel.updateMessage(updated)
            },
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun changeStatus(message: com.example.phoneforfilm.data.Message) {
        val statuses = arrayOf(
            getString(R.string.menu_status_sent),
            getString(R.string.menu_status_delivered),
            getString(R.string.menu_status_read)
        )
        AlertDialog.Builder(this)
            .setTitle(R.string.change_status)
            .setSingleChoiceItems(statuses, message.status) { dialog, which ->
                val updated = message.copy(status = which)
                viewModel.updateMessage(updated)
                dialog.dismiss()
            }
            .show()
    }

    private fun deleteMessage(message: com.example.phoneforfilm.data.Message) {
        viewModel.deleteMessage(message)
    }

    private fun togglePin(message: com.example.phoneforfilm.data.Message, pin: Boolean) {
        // Use 'pinned' parameter name
        val updated = message.copy(pinned = pin)
        viewModel.updateMessage(updated)
    }
}