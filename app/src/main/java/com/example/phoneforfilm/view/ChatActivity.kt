package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.MessageDatabase

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Initialize RecyclerView for messages
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMessages)
        // Set adapter and layoutManager, and load messages
        val adapter = MessageAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Load messages for this chat
        val chatId = intent.getLongExtra("chatId", -1L)
        val db = MessageDatabase.getInstance(this)
        db.messageDao().getMessagesForChat(chatId).observe(this) { messages ->
            adapter.submitList(messages)
        }
    }
}