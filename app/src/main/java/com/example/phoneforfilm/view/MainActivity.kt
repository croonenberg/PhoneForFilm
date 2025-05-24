package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ContactAdapter
import com.example.phoneforfilm.databinding.ActivityMainBinding
import com.example.phoneforfilm.ui.chat.ChatActivity
import com.example.phoneforfilm.ui.contact.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ContactViewModel by viewModels()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        adapter = ContactAdapter { contact ->
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra("conversationId", contact.id)
            }
            startActivity(intent)
        }

        binding.recyclerViewFilms.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFilms.adapter = adapter

        lifecycleScope.launch {
            viewModel.contacts.collectLatest { contacts ->
                adapter.submitList(contacts)
            }
        }
    }
}