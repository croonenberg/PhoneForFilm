package com.example.phoneforfilm.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {

    private val viewModel: ChatViewModel by viewModels()
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewChat)
        val editText = findViewById<EditText>(R.id.editTextMessage)
        val sendButton = findViewById<Button>(R.id.btnSend)

        messageAdapter = MessageAdapter()
        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.messages.observe(this) { messages ->
            messageAdapter.submitList(messages)
        }

        sendButton.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                viewModel.sendMessage(text)
                editText.text.clear()
            }
        }
    }
}
