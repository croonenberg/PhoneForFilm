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
import android.app.Activity
import android.content.Intent

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
        
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CREATE_CONTACT && resultCode == Activity.RESULT_OK) {
            val newContactId = data?.getIntExtra("contactId", -1) ?: -1
            if (newContactId != -1) {
                val resultData = Intent().putExtra("contactId", newContactId)
                setResult(Activity.RESULT_OK, resultData)
                finish()
            }
        }
    }

    companion object {
        private const val REQUEST_CREATE_CONTACT = 1002
    }

}

        binding.recyclerContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerContacts.adapter = adapter

        viewModel.allContacts.observe(this) { adapter.submitList(it) }

        binding.fabAddContact.setOnClickListener {
            startActivityForResult(
                Intent(this, EditContactActivity::class.java),
                REQUEST_CREATE_CONTACT
            )
        }
    }
}
