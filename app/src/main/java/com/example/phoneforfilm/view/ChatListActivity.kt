package com.example.phoneforfilm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import com.example.phoneforfilm.model.Conversation
import com.example.phoneforfilm.adapter.ConversationAdapter
import kotlinx.coroutines.launch
import android.provider.ContactsContract

class ChatListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatListBinding
    private val db by lazy { AppDatabase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabNewConversation.setOnClickListener { pickContact() }
        loadConversations()
    }

    override fun onResume() {
        super.onResume()
        loadConversations()
    }

    private fun pickContact() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, REQUEST_PICK_CONTACT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PICK_CONTACT && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data ?: return
            lifecycleScope.launch {
                val contactId = uri.lastPathSegment?.toLong() ?: return@launch
                // TODO: query ContactsContract to get real name & phone
                val name = "Unknown"
                val phone = ""
                val newContactId = db.contactDao().insertContact(name, phone, contactId).toInt()
                db.conversationDao().insertConversation(Conversation(contactId = newContactId, lastMessage = "", timestamp = System.currentTimeMillis()))
                runOnUiThread { loadConversations() }
            }
        }
    }

    private fun loadConversations() {
        lifecycleScope.launch {
            val convs = db.conversationDao().getAllNow()
            val contacts = db.contactDao().getAllNow()
            val names = contacts.associate { it.id to it.name }
            runOnUiThread {
                binding.recyclerView.adapter = ConversationAdapter(convs, names) { conv ->
                    startActivity(Intent(this@ChatListActivity, ChatActivity::class.java).apply {
                        putExtra("conversationId", conv.id)
                    })
                }
            }
        }
    }

    companion object {
        private const val REQUEST_PICK_CONTACT = 1001
    }
}
