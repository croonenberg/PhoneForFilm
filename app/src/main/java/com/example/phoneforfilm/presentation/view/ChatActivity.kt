package com.example.phoneforfilm.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.presentation.adapter.MessageAdapter
import com.example.phoneforfilm.presentation.adapter.MessageActionListener
import com.example.phoneforfilm.presentation.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity(), MessageActionListener {

    companion object {
        const val EXTRA_CONVERSATION_ID = "conversationId"
        const val EXTRA_SENDER_ID = "senderId"
    }

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter
    private var conversationId: Int = -1
    private var senderId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Lees intent-extras
        conversationId = intent.getIntExtra(EXTRA_CONVERSATION_ID, -1)
        senderId = intent.getIntExtra(EXTRA_SENDER_ID, -1)
        if (conversationId < 0 || senderId < 0) {
            finish()
            return
        }

        // RecyclerView en adapter instellen
        adapter = MessageAdapter(emptyList(), this as MessageActionListener, senderId)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // LiveData observer
        viewModel.loadMessagesForChat(conversationId)
        viewModel.messages.observe(this, Observer { messages ->
            adapter.updateMessages(messages)
            binding.recyclerView.scrollToPosition(messages.size - 1)
        })

        // Verzenden knop
        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                viewModel.sendMessage(text, conversationId, senderId)
                binding.etMessage.text?.clear()
            }
        }
    }

    /**
     * Callback voor lange druk op een bericht
     */
    fun onMessageLongPressed(message: Message) {
        // Verwijder bericht via ViewModel
        viewModel.deleteMessage(message)
    }
    override fun onMessageLongPressed(message: Message) {
        // Handle long-press action
    }

}