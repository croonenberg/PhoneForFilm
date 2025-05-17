package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ContactAdapter
import com.example.phoneforfilm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar instellen
        setSupportActionBar(binding.toolbar)

        // RecyclerView initialiseren met bestaande ContactAdapter
        adapter = ContactAdapter(emptyList())
        binding.recyclerViewFilms.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFilms.adapter = adapter

        // TODO: Vul 'adapter' later met echte data vanuit ViewModel/Repository
    }
}
