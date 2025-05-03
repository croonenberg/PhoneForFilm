package com.example.phoneforfilm.ui.contact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ContactAdapter
import com.example.phoneforfilm.databinding.ActivityContactPickerBinding
import com.example.phoneforfilm.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactPickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactPickerBinding
    private val viewModel by viewModels<ContactViewModel>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactAdapter { contact ->
            val data = Intent().putExtra("contactId", contact.id)
            setResult(Activity.RESULT_OK, data)
            finish()
        }

        binding.recyclerContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerContacts.adapter = adapter

        viewModel.allContacts.observe(this) { adapter.submitList(it) }
    }
}
