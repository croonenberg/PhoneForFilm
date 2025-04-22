package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import com.example.phoneforfilm.view.BaseActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ConversationAdapter
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatListActivity : BaseActivity() {

    private lateinit var binding: ActivityChatListBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        // Laad gesprekken bij start
        loadConversations()

        // Nieuwe conversatie + dummy bericht
        binding.fabNewConversation.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                // Dummy contact
                val contactId = db.contactDao().insert(Contact(name = "Dummy", phoneNumber = ""))
                // Dummy conversatie
                val convId = db.conversationDao().insert(
                    Conversation(
                        contactId = contactId.toInt(),
                        lastMessage = "Eerste bericht",
                        timestamp = System.currentTimeMillis()
                    )
                )
                // Dummy bericht
                db.messageDao().insertMessage(
                    Message(
                        chatId = convId.toInt(),
                        senderId = contactId,
                        text = "Hallo, dit is een dummybericht!",
                        timestamp = System.currentTimeMillis(),
                        status = 0,
                        pinned = false,
                        favorite = false,
                        isDeleted = false
                    )
                )
                // Terug naar UI en herlaad lijst
                withContext(Dispatchers.Main) {
                    loadConversations()
                }
            }
        }
    }

    private fun loadConversations() {
        lifecycleScope.launch {
            val conversations: List<Conversation> = withContext(Dispatchers.IO) {
                db.conversationDao().getAllNow()
            }
            val contacts = withContext(Dispatchers.IO) {
                db.contactDao().getAllNow()
            }
            val contactNameMap = contacts.associateBy({ it.id }, { it.name })
            val adapter = ConversationAdapter(conversations, contactNameMap) { conversation ->
                val intent = Intent(this@ChatListActivity, ChatActivity::class.java)
                intent.putExtra("CONVERSATION_ID", conversation.id.toLong())
                startActivity(intent)
            }
            binding.rvConversations.layoutManager = LinearLayoutManager(this@ChatListActivity)
            binding.rvConversations.adapter = adapter
        }
    }
}