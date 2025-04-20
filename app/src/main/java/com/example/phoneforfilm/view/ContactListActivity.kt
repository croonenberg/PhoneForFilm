package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityContactListBinding
import com.example.phoneforfilm.viewmodel.ContactViewModel

class ContactListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactListBinding
    private lateinit var viewModel: ContactViewModel
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        adapter = ContactAdapter(
            onClick = { contact ->
                val intent = Intent(this, EditContactActivity::class.java)
                intent.putExtra("contactId", contact.id)
                startActivity(intent)
            }
        )

        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewContacts.adapter = adapter

        viewModel.allContacts.observe(this) { contacts ->
            adapter.submitList(contacts)
        }

        binding.buttonAddContact.setOnClickListener {
            val intent = Intent(this, EditContactActivity::class.java)
            startActivity(intent)
        }
    }
}
