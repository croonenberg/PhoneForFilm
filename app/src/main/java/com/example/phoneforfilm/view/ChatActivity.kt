
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

class ChatActivity : BaseActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels {
        ChatViewModelFactory(MessageRepository(AppDatabase.getDatabase(this).messageDao()))
    }

    private val currentUserId = 1L
    private val otherId = 0L
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MessageAdapter(currentUserId).apply {
            onMessageEdit         = { editMessage(it) }
            onMessageTimeChange   = { editTime(it) }
            onMessageStatusChange = { changeStatus(it) }
            onToggleDirection     = { toggleDirection(it) }
            onMessageDelete       = { deleteMessage(it) }
        }

        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMessages.adapter = adapter

        val chatId = intent.getLongExtra("CONVERSATION_ID", -1L)
        viewModel.loadMessages(chatId)
        viewModel.messages.observe(this) { list ->
            adapter.messages = list
            adapter.notifyDataSetChanged()
            if (list.isNotEmpty()) binding.recyclerViewMessages.scrollToPosition(list.size - 1)
        }

        binding.buttonSend.setOnClickListener {
            val txt = binding.editTextMessage.text.toString()
            if (txt.isNotBlank()) {
                viewModel.sendMessage(chatId, txt, currentUserId)
                binding.editTextMessage.text?.clear()
            }
        }
    }

    // ----- helpers -----
    private fun editMessage(m: com.example.phoneforfilm.data.Message) {
        val input = EditText(this).apply {
            setText(m.text)
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
        }
        AlertDialog.Builder(this)
            .setTitle(R.string.edit_message)
            .setView(input)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                viewModel.updateMessage(m.copy(text = input.text.toString()))
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun editTime(m: com.example.phoneforfilm.data.Message) {
        val cal = Calendar.getInstance().apply { timeInMillis = m.timestamp }
        TimePickerDialog(this, { _, h, min ->
            val newTime = cal.apply {
                set(Calendar.HOUR_OF_DAY, h); set(Calendar.MINUTE, min)
            }.timeInMillis
            viewModel.updateMessage(m.copy(timestamp = newTime))
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }

    private fun changeStatus(m: com.example.phoneforfilm.data.Message) {
        val statuses = arrayOf(getString(R.string.status_sent), getString(R.string.status_delivered), getString(R.string.status_read))
        AlertDialog.Builder(this)
            .setItems(statuses) { _, which -> viewModel.updateMessage(m.copy(status = which)) }
            .show()
    }

    private fun toggleDirection(m: com.example.phoneforfilm.data.Message) {
        val newSender = if (m.senderId == currentUserId) otherId else currentUserId
        viewModel.updateMessage(m.copy(senderId = newSender))
    }

    private fun deleteMessage(m: com.example.phoneforfilm.data.Message) {
        viewModel.updateMessage(m.copy(isDeleted = true))
    }
}
