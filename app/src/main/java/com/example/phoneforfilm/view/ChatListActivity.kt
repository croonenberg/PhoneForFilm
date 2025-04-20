package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.ConversationRepository
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import com.example.phoneforfilm.viewmodel.ChatListViewModel
import com.example.phoneforfilm.viewmodel.ChatListViewModelFactory
import kotlinx.coroutines.launch

class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding

    private val viewModel: ChatListViewModel by viewModels {
        ChatListViewModelFactory(
            ConversationRepository(
                AppDatabase.getDatabase(this).conversationDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ConversationAdapter(
            onClick = { conversation ->
                val intent = Intent(this, ChatActivity::class.java)
                intent.putExtra("CONVERSATION_ID", conversation.id)
                startActivity(intent)
            },
            onDelete = { conversation ->
                viewModel.deleteConversation(conversation)
            }
        )

        binding.rvConversations.layoutManager = LinearLayoutManager(this)
        binding.rvConversations.adapter = adapter
        binding.rvConversations.contentDescription = getString(R.string.chat_list)
        binding.rvConversations.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES

        binding.fabNewConversation.setOnClickListener {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    val dao = AppDatabase.getDatabase(this@ChatListActivity).conversationDao()
                    val newId = dao.insert(
                        Conversation(
                            contactId = 0,
                            name = getString(R.string.new_conversation),
                            lastMessage = "",
                            timestamp = System.currentTimeMillis()
                        )
                    )
                    val intent = Intent(this@ChatListActivity, ChatActivity::class.java)
                    intent.putExtra("CONVERSATION_ID", newId)
                    startActivity(intent)
                }
            }
        }

        viewModel.allConversations.observe(this) { conversations ->
            adapter.submitList(conversations)
        }
    }
}
