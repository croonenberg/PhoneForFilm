package com.example.phoneforfilm.ui.contact

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityContactPickerBinding
import com.example.phoneforfilm.presentation.viewmodel.ContactViewModel
import com.example.phoneforfilm.presentation.viewmodel.ChatViewModel
import com.example.phoneforfilm.presentation.view.ChatActivity
import com.example.phoneforfilm.ui.contact.ContactPickerAdapter
import com.example.phoneforfilm.view.EditContactActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactPickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactPickerBinding
    private val contactViewModel: ContactViewModel by viewModels()
    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactPickerAdapter { selectedContact ->
            chatViewModel.createChatFor(selectedContact.id) { newChatId ->
                val intent = Intent(this, ChatActivity::class.java).apply {
                    putExtra(ChatActivity.EXTRA_CONVERSATION_ID, newChatId)
                    putExtra(ChatActivity.EXTRA_SENDER_ID, selectedContact.id)
                    putExtra(ChatActivity.EXTRA_CONTACT_NAME, selectedContact.name)
                }
                startActivity(intent)
                finish()
            }
        }

        binding.recyclerContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerContacts.adapter = adapter

        lifecycleScope.launch {
            contactViewModel.contacts.collect { list ->
                adapter.submitList(list)
            }
        }

        binding.fabAddContact.setOnClickListener {
            startActivity(Intent(this, EditContactActivity::class.java))
        }
    }
}
