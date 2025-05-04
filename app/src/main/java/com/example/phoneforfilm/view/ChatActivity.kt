package com.example.phoneforfilm.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.EditText
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private var chatId: Int = -1
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatId = intent.getIntExtra("chatId", -1)
        viewModel.loadMessages(chatId)

        adapter = MessageAdapter(mutableListOf())
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMessages.adapter = adapter

        viewModel.messages.observe(this) {
            adapter.updateData(it)
            binding.recyclerViewMessages.scrollToPosition(it.size - 1)
        }

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                val msg = Message(conversationId = chatId, text = text, timestamp = System.currentTimeMillis(), isSender = true)
                viewModel.sendMessage(msg)
                binding.editTextMessage.text?.clear()
            }
        }
    }

    fun onEditMessage(message: Message) {
        val input = EditText(this)
        input.setText(message.text)
        AlertDialog.Builder(this)
            .setTitle("Edit Message")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val newText = input.text.toString().trim()
                if (newText.isNotEmpty()) {
                    viewModel.updateMessage(message.copy(text = newText))
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    fun onDeleteMessage(message: Message) {
        AlertDialog.Builder(this)
            .setTitle("Delete Message")
            .setMessage("Are you sure you want to delete this message?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteMessage(message)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    fun onCopyMessage(message: Message) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("message", message.text))
        Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    fun onChangeTheme() {
        val themes = arrayOf("Greenroom","Blue Stage","Grey Card","Neutral Light","Darkroom")
        AlertDialog.Builder(this)
            .setTitle("Select Theme")
            .setItems(themes) { _, which ->
                Toast.makeText(this, "Theme: ${themes[which]}", Toast.LENGTH_SHORT).show()
                // TODO: call viewModel or repository to save theme for chatId
            }
            .show()
    }

    fun onChangeLanguage() {
        val languages = arrayOf("Nederlands","English","Deutsch","Français","Español")
        AlertDialog.Builder(this)
            .setTitle("Select Language")
            .setItems(languages) { _, which ->
                Toast.makeText(this, "Language: ${languages[which]}", Toast.LENGTH_SHORT).show()
                // TODO: call preferences helper or viewModel to apply language
            }
            .show()
    }

    fun onToggleSender(message: Message) {
        viewModel.updateMessage(message.copy(isSender = !message.isSender))
    }
}
