
package com.example.phoneforfilm.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.data.MessageRepository
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.viewmodel.ChatViewModelFactory

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var messageAdapter: MessageAdapter
    private var selectedMessage: Message? = null
    private var editMode: Boolean = false

    private val viewModel: ChatViewModel by viewModels {
        val database = AppDatabase.getDatabase(applicationContext)
        ChatViewModelFactory(MessageRepository(database.messageDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatId = intent.getIntExtra("chatId", 0)

        messageAdapter = MessageAdapter { message ->
            showMessageOptions(message)
        }

        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = messageAdapter
        }

        viewModel.messages.observe(this) { messages ->
            messageAdapter.submitList(messages)
        }

        viewModel.loadMessages(chatId)

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotBlank()) {
                if (editMode && selectedMessage != null) {
                    val updatedMessage = selectedMessage!!.copy(text = text)
                    viewModel.updateMessage(updatedMessage)
                    exitEditMode()
                } else {
                    viewModel.sendMessage(chatId, text)
                }
                binding.editTextMessage.text.clear()
            }
        }
    }

    private fun showMessageOptions(message: Message) {
        val popup = PopupMenu(this, binding.editTextMessage)
        popup.menuInflater.inflate(R.menu.message_options_menu, popup.menu)
        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_edit -> {
                    enterEditMode(message)
                    true
                }
                R.id.menu_delete -> {
                    viewModel.deleteMessage(message)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun enterEditMode(message: Message) {
        selectedMessage = message
        editMode = true
        binding.editTextMessage.setText(message.text)
        binding.buttonSend.text = getString(R.string.update)
    }

    private fun exitEditMode() {
        selectedMessage = null
        editMode = false
        binding.buttonSend.text = getString(R.string.send)
    }
}
