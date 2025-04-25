package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.ContactDao
import com.example.phoneforfilm.adapter.ContactAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatListActivity : AppCompatActivity() {

    private lateinit var contactDao: ContactDao
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        // Initialize DAO and Adapter
        val db = AppDatabase.getDatabase(applicationContext)
        contactDao = db.contactDao()
        adapter = ContactAdapter()
        findViewById<RecyclerView>(R.id.rvConversations).adapter = adapter

        // Load contacts in background
        lifecycleScope.launch(Dispatchers.IO) {
            val contacts = contactDao.getAllNow()
            withContext(Dispatchers.Main) {
                adapter.submitList(contacts)
            }
        }
    }
}
