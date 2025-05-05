package com.example.phoneforfilm.view

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import com.example.phoneforfilm.data.local.db.AppDatabase
import com.example.phoneforfilm.data.model.Contact
import com.example.phoneforfilm.databinding.ActivityEditContactBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.app.Activity
import android.content.Intent

@AndroidEntryPoint
class EditContactActivity : BaseActivity() {

    private lateinit var binding: ActivityEditContactBinding
    private var androidContactId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        androidContactId = intent.getIntExtra("contactId", -1)

        binding.buttonSaveContact.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val phoneNumber = binding.editTextPhone.text.toString()
            if (name.isNotBlank() && phoneNumber.isNotBlank()) {
                val newContact = Contact(id = androidContactId, name = name, phoneNumber = phoneNumber)
                CoroutineScope(Dispatchers.IO).launch {
                    val insertedId: Long = AppDatabase.getDatabase(this@EditContactActivity)
                        .contactDao()
                        .insert(newContact)

                    withContext(Dispatchers.Main) {
                        val data = Intent().putExtra("contactId", insertedId.toInt())
                        setResult(Activity.RESULT_OK, data)
                        finish()
                    }
                }
            }
        }
    }
}
