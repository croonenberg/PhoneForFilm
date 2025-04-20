package com.example.phoneforfilm.view

import androidx.activity.viewModels
import android.os.Bundle
import android.widget.EditText
import android.app.TimePickerDialog
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

    private fun showMessageOptions(message: com.example.phoneforfilm.data.Message) {
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

    private fun editMessage(message: com.example.phoneforfilm.data.Message) {
        val input = EditText(this).apply {
            setText(message.text)
            inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE
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
        TimePickerDialog(this, { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val updated = message.copy(timestamp = cal.timeInMillis)
            viewModel.updateMessage(updated)
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }

    private fun changeStatus(message: com.example.phoneforfilm.data.Message) {
        val statuses = arrayOf(
            getString(R.string.status_sent),
            getString(R.string.status_delivered),
            getString(R.string.status_read)
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

    private fun changeSender(message: com.example.phoneforfilm.data.Message) {
        val updated = message.copy(senderId = if (message.senderId == currentUserId) 2L else currentUserId)
        viewModel.updateMessage(updated)
    }

    private fun togglePin(message: com.example.phoneforfilm.data.Message, pin: Boolean) {
        val updated = message.copy(pinned = pin)
        viewModel.updateMessage(updated)
    }

    private fun toggleFavorite(message: com.example.phoneforfilm.data.Message, fav: Boolean) {
        val updated = message.copy(favorite = fav)
        viewModel.updateMessage(updated)
    }

    private fun deleteMessage(message: com.example.phoneforfilm.data.Message) {
        viewModel.deleteMessage(message)
    }
}


    private fun showEditMessageDialog(message: Message) {
        val input = EditText(this).apply {
            setText(message.text)
        }

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.edit_message))
            .setView(input)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val newText = input.text.toString()
                val updated = message.copy(text = newText)
                viewModel.update(updated)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showTimeChangeDialog(message: Message) {
        val input = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_DATETIME
            hint = "New timestamp (ms)"
        }

        AlertDialog.Builder(this)
            .setTitle("Change time")
            .setView(input)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val newTime = input.text.toString().toLongOrNull()
                newTime?.let {
                    val updated = message.copy(timestamp = it)
                    viewModel.update(updated)
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showStatusChangeDialog(message: Message) {
        val statuses = arrayOf("sent", "delivered", "read")
        AlertDialog.Builder(this)
            .setTitle("Change status")
            .setItems(statuses) { _, which ->
                val updated = message.copy(status = statuses[which])
                viewModel.update(updated)
            }
            .show()
    }
