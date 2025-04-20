package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.databinding.ActivityEditContactBinding
import com.example.phoneforfilm.viewmodel.ContactViewModel

class EditContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditContactBinding
    private lateinit var viewModel: ContactViewModel
    private var contactId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        contactId = intent.getIntExtra("contactId", -1).takeIf { it != -1 }

        contactId?.let {
            val contact = viewModel.getContactById(it)
            contact?.let { c ->
                binding.editTextName.setText(c.name)
                binding.editTextPhone.setText(c.phoneNumber)
            }
        }

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val phone = binding.editTextPhone.text.toString()

            if (name.isNotBlank()) {
                val contact = Contact(id = contactId ?: 0, name = name, phoneNumber = phone)
                if (contactId == null) {
                    viewModel.insert(contact)
                } else {
                    viewModel.update(contact)
                }
                finish()
            }
        }
    }
}
