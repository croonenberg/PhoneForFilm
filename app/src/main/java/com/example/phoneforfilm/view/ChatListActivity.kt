package com.example.phoneforfilm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.phoneforfilm.view.ChatActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ConversationAdapter
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import com.example.phoneforfilm.ui.contact.ContactPickerActivity
import android.content.Intent
import com.example.phoneforfilm.view.ChatActivity
import com.example.phoneforfilm.viewmodel.ChatListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding
    private lateinit var adapter: ConversationAdapter
    private val viewModel by viewModels<ChatListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ConversationAdapter(emptyList()) { convo ->
            startActivity(
                Intent(this, ChatActivity::class.java)
                    .putExtra("chatId", convo.id)
            )
        }

        binding.rvConversations.layoutManager = LinearLayoutManager(this)
        binding.rvConversations.adapter = adapter

        viewModel.conversations.observe(this) { adapter.submitList(it) }

        // Plus‑knop → contact‑picker
        binding.fabNewConversation.setOnClickListener {
            startActivityForResult(
                Intent(this, ContactPickerActivity::class.java),
                REQUEST_PICK_CONTACT
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
if (requestCode == REQUEST_PICK_CONTACT && resultCode == Activity.RESULT_OK) {
            val contactId = data?.getIntExtra("contactId", -1) ?: -1
            if (contactId != -1) {
                viewModel.createChatFor(contactId) { chatId ->
                    startActivity(Intent(this, ChatActivity::class.java)
                        .putExtra("chatId", chatId))
                }
            }
        }

    }

    companion object {
        private const val REQUEST_PICK_CONTACT = 1001
    }
}
