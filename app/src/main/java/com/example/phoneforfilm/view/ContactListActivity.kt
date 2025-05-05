package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityContactListBinding
import com.example.phoneforfilm.presentation.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope


@AndroidEntryPoint
class ContactListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactListBinding
    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(this)

        val adapter = ContactListAdapter(
            onDelete = { contact -> viewModel.delete(contact) },
            onEdit = { contact -> /* TODO: open EditContactActivity */ }
        )
        binding.recyclerViewContacts.adapter = adapter

        lifecycleScope.launch {
            viewModel.contacts.collect { contacts ->
                adapter.submitList(contacts)
            }
        }

        binding.fabAddContact.setOnClickListener {
            // TODO: open AddContactActivity
        }
    }
}
