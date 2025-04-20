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
                val adapter = ArrayAdapter(this@EditConversationActivity, android.R.layout.simple_spinner_item, names)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerContacts.adapter = adapter
            }
        }

        binding.buttonSaveConversation.setOnClickListener {
            val message = binding.editTextMessage.text.toString()
            val selectedPosition = binding.spinnerContacts.selectedItemPosition
            if (selectedPosition >= 0 && message.isNotBlank()) {
                selectedContactId = contacts[selectedPosition].id
                val conversation = Conversation(
                    contactId = selectedContactId,
                    lastMessage = message,
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
