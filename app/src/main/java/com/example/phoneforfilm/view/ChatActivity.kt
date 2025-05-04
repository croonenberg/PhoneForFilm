package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.R
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private var conversationId: Int = -1
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        conversationId = intent.getIntExtra("chatId", -1)
        adapter = MessageAdapter(this, emptyList())

        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMessages.adapter = adapter

        viewModel.getMessages(conversationId).observe(this) { list ->
            adapter.update(list)
        }

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotBlank()) {
                viewModel.sendMessage(Message(conversationId = conversationId, text = text, timestamp = System.currentTimeMillis(), isSender = true))
                binding.editTextMessage.text.clear()
            }
        }
    }

    fun onMessageLongPressed(msg: Message) {
        val options = arrayOf(
            getString(R.string.edit_message),
            getString(R.string.delete_message),
            getString(R.string.choose_theme),
            getString(R.string.language_dutch),
            getString(R.string.language_english),
            getString(R.string.language_german),
            getString(R.string.language_french),
            getString(R.string.language_spanish)
        )
        AlertDialog.Builder(this)
            .setTitle(R.string.message_options)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showEditDialog(msg)
                    1 -> viewModel.deleteMessage(msg)
                    2 -> showThemeChooser()
                    in 3..7 -> showLanguageChooser()
                }
            }
            .show()
    }

    private fun showEditDialog(msg: Message) {
        val input = android.widget.EditText(this).apply {
            setText(msg.text)
            setSelection(msg.text.length)
        }
        AlertDialog.Builder(this)
            .setTitle(R.string.edit_message)
            .setView(input)
            .setPositiveButton(R.string.save) { _, _ ->
                val newText = input.text.toString()
                if (newText.isNotBlank()) {
                    val updatedMsg = msg.copy(text = newText)
                    viewModel.updateMessage(updatedMsg)
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showThemeChooser() {
        val themeNames = arrayOf(
            getString(R.string.theme_greenroom),
            getString(R.string.theme_blue_stage),
            getString(R.string.theme_grey_card),
            getString(R.string.theme_neutral_light),
            getString(R.string.theme_darkroom)
        )
        AlertDialog.Builder(this)
            .setTitle(R.string.choose_theme)
            .setItems(themeNames) { _, which ->
                // TODO: apply theme
            }
            .show()
    }

    private fun showLanguageChooser() {
        val languages = arrayOf(
            getString(R.string.language_dutch),
            getString(R.string.language_english),
            getString(R.string.language_german),
            getString(R.string.language_french),
            getString(R.string.language_spanish)
        )
        AlertDialog.Builder(this)
            .setTitle(R.string.choose_language)
            .setItems(languages) { _, which ->
                // TODO: apply language
            }
            .show()
    }
}
