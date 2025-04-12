package com.example.phoneforfilm

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity() {

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        db = AppDatabase.getDatabase(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewChat)
        val editText = findViewById<EditText>(R.id.editTextMessage)
        val sendButton = findViewById<Button>(R.id.btnSend)

        messageAdapter = MessageAdapter()
        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadMessages()

        sendButton.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                addMessage(text, true)
                editText.text.clear()

                Handler().postDelayed({
                    addMessage("Je zei: $text", false)
                }, 2000)
            }
        }
    }

    private fun addMessage(content: String, sentByUser: Boolean) {
        val message = Message(0, content, System.currentTimeMillis(), sentByUser)
        CoroutineScope(Dispatchers.IO).launch {
            db.messageDao().insert(message)
            loadMessages()
        }
    }

    private fun loadMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            val messages = db.messageDao().getAll()
            runOnUiThread {
                messageAdapter.submitList(messages)
            }
        }
    }
}
