package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // TODO: initialize RecyclerView, adapter, viewModel, and load messages
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewChat)
        // recyclerView.adapter = ...
    }
}
