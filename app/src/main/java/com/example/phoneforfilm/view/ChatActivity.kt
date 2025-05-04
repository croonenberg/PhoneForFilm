package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.phoneforfilm.data.model.Message

/**
 * Chat‑scherm: toont berichten, verstuurt nieuwe en reageert op lange‑druk
 */
@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        observeMessages()

        // Start conversatie die door Intent is meegegeven
        val conversationId = intent.getIntExtra("conversationId", -1)
        viewModel.startConversation(conversationId)

        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotBlank()) {
                viewModel.sendMessage(text)
                binding.editTextMessage.text?.clear()
            }
        }
    }

    private fun initRecyclerView() {
        adapter = MessageAdapter(this, emptyList())
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMessages.adapter = adapter
    }

    private fun observeMessages() {
        viewModel.messages.observe(this) { list ->
            adapter.update(list)
            binding.recyclerViewMessages.scrollToPosition(list.size - 1)
        }
    }

    /** Callback uit adapter bij lang indrukken op bericht */
    fun onMessageLongPressed(msg: Message) {
        // TODO: context‑menu tonen / bericht bewerken
    }
}
