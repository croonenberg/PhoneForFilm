package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.AppDatabase

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMessages)
        val chatIdLong = intent.getLongExtra("chatId", -1L)
        val chatId = chatIdLong.toInt()
        val currentUserId = chatIdLong

        val adapter = MessageAdapter(currentUserId)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val db = AppDatabase.getDatabase(this)
        db.messageDao()
            .getMessagesForChat(chatId)
            .observe(this) { messages ->
                adapter.submitList(messages)
            }
    }
}
