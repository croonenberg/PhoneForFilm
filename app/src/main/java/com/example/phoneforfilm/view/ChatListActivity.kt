package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ConversationAdapter
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDatabase(this)

        lifecycleScope.launch {
            val conversations: List<Conversation> = withContext(Dispatchers.IO) {
                db.conversationDao().getAllNow()
            }
            val contacts = withContext(Dispatchers.IO) {
                db.contactDao().getAllNow()
            }

            val contactNameMap = contacts.associateBy({ it.id }, { it.name })

            val adapter = ConversationAdapter(
                conversations,
                contactNameMap
            ) { conversation ->
                val intent = Intent(this@ChatListActivity, ChatActivity::class.java)
                intent.putExtra("CONVERSATION_ID", conversation.id)
                startActivity(intent)
            }

            binding.rvConversations.layoutManager = LinearLayoutManager(this@ChatListActivity)
            binding.rvConversations.adapter = adapter
        }
    }
}
