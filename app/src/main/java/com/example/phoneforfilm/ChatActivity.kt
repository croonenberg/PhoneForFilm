package com.example.phoneforfilm

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.data.MessageRepository
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val contactId = 0 // of haal uit intent‑extras
    private val vm: ChatViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repo = MessageRepository(
                    AppDatabase
                        .getDatabase(this@ChatActivity)
                        .messageDao()
                )
                @Suppress("UNCHECKED_CAST")
                return ChatViewModel(repo) as T
            }
        }
    }

    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adapter met correcte OnMessageLongClick‑implementatie
        adapter = MessageAdapter(object : MessageAdapter.OnMessageLongClick {
            override fun onLongClick(message: Message, anchor: View) {
                showStatusPicker(message)
            }
        })

        // RecyclerView instellen
        binding.recyclerViewChat.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewChat.adapter = adapter

        // LiveData observer op vm.messages(contactId)
        vm.messages(contactId).observe(this) { list ->
            adapter.submitList(list)
            binding.recyclerViewChat.scrollToPosition(list.size - 1)
        }

        // Verzenden van nieuw bericht met juiste constructor
        binding.btnSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                val msg = Message(
                    contactId = contactId,
                    timestamp = System.currentTimeMillis(),
                    text = text,
                    isSent = true,
                    status = "sent"
                )
                vm.insert(msg)
                binding.editTextMessage.text?.clear()
            }
        }
    }

    private fun showStatusPicker(msg: Message) {
        val statuses = arrayOf("sent", "received", "read")
        MaterialAlertDialogBuilder(this)
            .setTitle("Change status")
            .setItems(statuses) { _, which ->
                vm.update(msg.copy(status = statuses[which]))
            }
            .show()
    }
}
