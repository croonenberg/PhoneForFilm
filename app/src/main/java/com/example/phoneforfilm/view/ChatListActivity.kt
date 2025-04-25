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

class ChatListActivity : AppCompatActivity() {

    private lateinit var contactDao: ContactDao
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        // Initialiseer DAO en Adapter
        contactDao = /* verkrijg ContactDao instance, bv. via Room.databaseBuilder... */
        adapter = ContactAdapter()
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
