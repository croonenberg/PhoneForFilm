package com.example.phoneforfilm.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.databinding.ActivityEditConversationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditConversationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditConversationBinding
    private lateinit var contacts: List<Contact>
    private var selectedContactId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDatabase(this)
        CoroutineScope(Dispatchers.IO).launch {
            contacts = db.contactDao().getAllNow()
            val names = contacts.map { it.name }

            runOnUiThread {
                val adapter = ArrayAdapter(
                    this@EditConversationActivity,
                    android.R.layout.simple_spinner_item,
                    names
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerContacts.adapter = adapter
            }
        }

        binding.buttonSaveConversation.setOnClickListener {
            val messageText = binding.editTextConversationMessage.text.toString()
            val position = binding.spinnerContacts.selectedItemPosition
            if (position >= 0 && messageText.isNotBlank()) {
                selectedContactId = contacts[position].id
                val conversation = Conversation(
                    contactId = selectedContactId,
                    lastMessage = messageText,
                    timestamp = System.currentTimeMillis()
                )
                CoroutineScope(Dispatchers.IO).launch {
                    db.conversationDao().insert(conversation)
                    finish()
                }
            }
        }
    }
}
