package com.example.phoneforfilm.view

import androidx.activity.viewModels
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

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
            onClick = { conv ->
                val intent = Intent(this, ChatActivity::class.java)
                intent.putExtra("CONVERSATION_ID", conv.id)
                startActivity(intent)
            },
            onLongClick = { conv ->
                viewModel.deleteConversation(conv)
            }
        )

        binding.rvConversations.apply {
            layoutManager = LinearLayoutManager(this@ChatListActivity)
            this.adapter = adapter
            contentDescription = getString(R.string.chat_history)
            importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
        }

        binding.fabNewConversation.setOnClickListener {
            lifecycleScope.launch {
                val contacts = AppDatabase.getDatabase(this@ChatListActivity)
                    .contactDao().getAllList()
                val names = contacts.map { it.name }.toTypedArray()
                AlertDialog.Builder(this@ChatListActivity)
                    .setTitle(R.string.new_conversation)
                    .setItems(names) { dialogInterface, which ->
                        val contact = contacts[which]
                        lifecycleScope.launch {
                            val convId = ConversationRepository(
                                AppDatabase.getDatabase(this@ChatListActivity).conversationDao()
                            ).insert(
                                Conversation(
                                    contactId = contact.id.toLong(),
                                    lastMessage = null,
                                    timestamp = System.currentTimeMillis()
                                )
                            )
                            val intent = Intent(
                                this@ChatListActivity,
                                ChatActivity::class.java
                            )
                            intent.putExtra("CONVERSATION_ID", convId)
                            startActivity(intent)
                        }
                    }
                    .show()
            }
        }

        viewModel.allConversations.observe(this) { list ->
            adapter.submitList(list)
        }
    }
}
