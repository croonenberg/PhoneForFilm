package com.example.phoneforfilm.view

import dagger.hilt.android.AndroidEntryPoint

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ContactAdapter
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.databinding.ActivityContactListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ContactListActivity : BaseActivity() {

    private lateinit var binding: ActivityContactListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            val contacts = db.contactDao().getAllNow()

            withContext(Dispatchers.Main) {
                val adapter = ContactAdapter()
                binding.recyclerViewContacts.layoutManager = LinearLayoutManager(this@ContactListActivity)
                adapter.submitList(contacts)
                binding.recyclerViewContacts.adapter = adapter
            }
        }

        binding.buttonAddContact.setOnClickListener {
            startActivity(Intent(this, EditContactActivity::class.java))
        }
    }
}