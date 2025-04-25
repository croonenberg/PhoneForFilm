package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Initialize RecyclerView for messages
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMessages)
        // TODO: set adapter and layoutManager, and load messages
    }
}
