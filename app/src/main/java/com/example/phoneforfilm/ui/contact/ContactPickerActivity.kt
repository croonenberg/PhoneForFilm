package com.example.phoneforfilm.ui.contact

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityContactPickerBinding
import com.example.phoneforfilm.ui.chat.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactPickerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactPickerBinding
    private val contactViewModel: ContactViewModel by viewModels()
    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contacts = contactViewModel.contacts.value
        val adapter = ContactPickerAdapter(contacts) { contact ->
            // handle contact selection
        }
        binding.recyclerContacts.adapter = adapter
    }
}
