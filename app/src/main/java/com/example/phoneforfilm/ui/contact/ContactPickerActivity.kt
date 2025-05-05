package com.example.phoneforfilm.ui.contact

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityContactPickerBinding
import com.example.phoneforfilm.presentation.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope

@AndroidEntryPoint
class ContactPickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactPickerBinding
    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactPickerAdapter { selectedContact ->
            // TODO: Handle selected contact
        }

        binding.recyclerContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerContacts.adapter = adapter

        lifecycleScope.launch {
            viewModel.contacts.collect { list ->
                adapter.submitList(list)
            }
        }

        binding.fabAddContact.setOnClickListener {
            // TODO: Open AddContactActivity
        }
    }
}
