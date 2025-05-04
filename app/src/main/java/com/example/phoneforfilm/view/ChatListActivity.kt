package com.example.phoneforfilm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ConversationAdapter
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import com.example.phoneforfilm.ui.contact.ContactPickerActivity
import dagger.hilt.android.AndroidEntryPoint
import com.example.phoneforfilm.viewmodel.ChatListViewModel

@AndroidEntryPoint
class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding
    private val viewModel by viewModels<ChatListViewModel>()
    private lateinit var adapter: ConversationAdapter
    private lateinit var pickContactLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pickContactLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val contactId = result.data
                    ?.getIntExtra("contactId", -1) ?: return@registerForActivityResult
                viewModel.createChatFor(contactId) { chatId ->
                    startActivity(
                        Intent(this, ChatActivity::class.java)
                            .putExtra("chatId", chatId)
                    )
                }
            }
        }

        adapter = ConversationAdapter { conversation ->
            startActivity(
                Intent(this, ChatActivity::class.java)
                    .putExtra("chatId", conversation.id)
            )
        }

        binding.rvConversations.layoutManager = LinearLayoutManager(this)
        binding.rvConversations.adapter = adapter

        viewModel.conversations.observe(this) { list ->
            adapter.submitList(list)
        }

        binding.fabNewConversation.setOnClickListener {
            pickContactLauncher.launch(
                Intent(this, ContactPickerActivity::class.java)
            )
        }
    }
}
