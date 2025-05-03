package com.example.phoneforfilm.ui.contact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ContactAdapter
import com.example.phoneforfilm.databinding.ActivityContactPickerBinding
import com.example.phoneforfilm.view.EditContactActivity
import com.example.phoneforfilm.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity for selecting an existing contact or creating a new one.
 */
@AndroidEntryPoint
class ContactPickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactPickerBinding
    private val viewModel by viewModels<ContactViewModel>()
    private lateinit var adapter: ContactAdapter
    private lateinit var createContactLauncher: ActivityResultLauncher<Intent>

    /**
     * Initializes UI and registers the result launcher for the EditContact activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createContactLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val newContactId = result.data
                    ?.getIntExtra("contactId", -1) ?: return@registerForActivityResult
                setResult(Activity.RESULT_OK, Intent().putExtra("contactId", newContactId))
                finish()
            }
        }

        adapter = ContactAdapter { contact ->
            val data = Intent().putExtra("contactId", contact.id)
            setResult(Activity.RESULT_OK, data)
            finish()
        }

        binding.recyclerContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerContacts.adapter = adapter

        viewModel.allContacts.observe(this) { list ->
            adapter.submitList(list)
        }

        binding.fabAddContact.setOnClickListener {
            createContactLauncher.launch(
                Intent(this, EditContactActivity::class.java)
            )
        }
    }
}
