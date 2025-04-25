package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.ContactDao
import com.example.phoneforfilm.adapter.ContactAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Intent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.AppDatabase

class ChatListActivity : AppCompatActivity() {

    private lateinit var contactDao: ContactDao
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        // Handle new conversation button
        findViewById<FloatingActionButton>(R.id.fabNewConversation).setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        // Initialiseer DAO en Adapter
        contactDao = AppDatabase.getDatabase(this).contactDao()
        adapter = ContactAdapter()
        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvConversations)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        findViewById<RecyclerView>(R.id.recyclerViewContacts).adapter = adapter

        // Laden van contacten in achtergrondthread om main thread niet te blokkeren
        lifecycleScope.launch(Dispatchers.IO) {
            val contacts = contactDao.getAllNow()
            withContext(Dispatchers.Main) {
                adapter.submitList(contacts)
            }
        }
    }
}