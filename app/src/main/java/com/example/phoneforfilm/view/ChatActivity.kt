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
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewChat)
        val editTextMessage = findViewById<EditText>(R.id.editTextMessage)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val btnChangeTheme = findViewById<Button>(R.id.btnChangeTheme)

        adapter = MessageAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.messages.observe(this) { messages ->
            adapter.submitList(messages)
            recyclerView.scrollToPosition(messages.size - 1)
        }

        btnSend.setOnClickListener {
            val text = editTextMessage.text.toString()
            if (text.isNotEmpty()) {
                viewModel.sendMessage(text)
                editTextMessage.text.clear()
            }
        }

        btnChangeTheme.setOnClickListener {
            val themes = arrayOf("Greenroom", "Blue Stage", "Grey Card", "Neutral Light", "Darkroom")
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Kies Thema")
                .setItems(themes) { _, which ->
                    viewModel.setChatTheme(which)
                }
                .show()
        }
    }
}
