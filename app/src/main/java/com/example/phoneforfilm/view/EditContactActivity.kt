package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.view.BaseActivity
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.databinding.ActivityEditContactBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditContactActivity : BaseActivity() {

    private lateinit var binding: ActivityEditContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSaveContact.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val phoneNumber = binding.editTextPhone.text.toString()
            if (name.isNotBlank() && phoneNumber.isNotBlank()) {
                val newContact = Contact(name = name, phoneNumber = phoneNumber)
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase.getDatabase(this@EditContactActivity)
                        .contactDao()
                        .insert(newContact)
                    finish()
                }
            }
        }
    }
}
