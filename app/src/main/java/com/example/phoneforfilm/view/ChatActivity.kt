package com.example.phoneforfilm.view

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.R
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.MessageRepository
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.viewmodel.ChatViewModelFactory
import java.util.Calendar

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

        // 1) Adapter zonder lambda‑constructor
        adapter = MessageAdapter()

        // 2) Callback‑properties instellen
        adapter.onMessageEdit         = { message -> editMessage(message) }
        adapter.onMessageTimeChange   = { message -> editTime(message) }
        adapter.onMessageStatusChange = { message -> changeStatus(message) }
        adapter.onMessageDelete       = { message -> deleteMessage(message) }
        adapter.onMessagePinToggle    = { message ->
            // togglet pin-status
            togglePin(message, !message.isPinned)
        }

        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = this@ChatActivity.adapter
        }

        // 3) ViewModel: berichten laden en observeren
        val chatId = intent.getLongExtra("CONVERSATION_ID", -1L)
        viewModel.loadMessages(chatId)  // veronderstelde methode in ViewModel
        viewModel.messages.observe(this) { list ->
            // manueel bijwerken, i.p.v. submitList
            adapter.messages = list
            adapter.notifyDataSetChanged()
            if (list.isNotEmpty()) {
                binding.recyclerViewMessages
                    .scrollToPosition(list.size - 1)
            }
        }

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotBlank()) {
                viewModel.sendMessage(chatId, text, currentUserId)  // veronderstelde methode
                binding.editTextMessage.text?.clear()
            }
        }
    }

    // 4) Bestaande helper‑methodes blijven binnen de class
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
        val updated = message.copy(isPinned = pin)
        viewModel.updateMessage(updated)
    }
}
