package com.example.phoneforfilm.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.model.Message
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.R

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: MessageAdapter
    private val viewModel: ChatViewModel by viewModels()

    private var contactId: Int = 0
    private var contactName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactId = intent.getIntExtra(EXTRA_CONTACT_ID, 0)
        contactName = intent.getStringExtra(EXTRA_CONTACT_NAME) ?: ""

        supportActionBar?.title = contactName

        val database = AppDatabase.getDatabase(this)
        viewModel.setMessageDao(database.messageDao())

        adapter = MessageAdapter(emptyList())
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMessages.adapter = adapter

        observeMessages()

        binding.buttonSend.setOnClickListener {
            val messageText = binding.editTextMessage.text.toString()
            if (messageText.isNotBlank()) {
                viewModel.insertMessage(
                    Message(
                        contactId = contactId,
                        content = messageText,
                        timestamp = System.currentTimeMillis(),
                        isSent = true,
                        status = 1 // 1 = verzonden
                    )
                )
                binding.editTextMessage.text.clear()
            } else {
                Toast.makeText(this, getString(R.string.enter_message), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeMessages() {
        viewModel.getMessagesForContact(contactId).observe(this) { messages ->
            adapter.updateMessages(messages)
            binding.recyclerViewMessages.scrollToPosition(messages.size - 1)
        }
    }

    companion object {
        private const val EXTRA_CONTACT_ID = "extra_contact_id"
        private const val EXTRA_CONTACT_NAME = "extra_contact_name"

        fun newIntent(context: Context, contactId: Int = 0, contactName: String = ""): Intent {
            return Intent(context, ChatActivity::class.java).apply {
                putExtra(EXTRA_CONTACT_ID, contactId)
                putExtra(EXTRA_CONTACT_NAME, contactName)
            }
        }
    }
}
