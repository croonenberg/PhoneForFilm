package com.example.phoneforfilm.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import com.example.phoneforfilm.adapter.ConversationAdapter

class ChatListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatListBinding
    private lateinit var adapter: ConversationAdapter
    private lateinit var pickContactLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ConversationAdapter(emptyList(), emptyMap()) { conversation ->
            Intent(this, ChatActivity::class.java).apply {
                putExtra("conversationId", conversation.id)
                startActivity(this)
            }
        }

        binding.rvConversations.layoutManager = LinearLayoutManager(this)
        binding.rvConversations.adapter = adapter

        pickContactLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { uri ->
                    handlePickedContact(uri)
                }
            }
        }

        binding.fabNewConversation.setOnClickListener {
            val pickIntent = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            pickContactLauncher.launch(pickIntent)
        }

        val db = AppDatabase.getDatabase(applicationContext)
        val conversationDao = db.conversationDao()
        val contactDao = db.contactDao()

        conversationDao.getAll().observe(this, Observer { convs ->
            val namesMap = contactDao.getAllNow()
                .associate { it.id to it.displayName }
            adapter.updateData(convs, namesMap)
        })
    }

    private fun handlePickedContact(uri: Uri) {
        contentResolver.query(
            uri,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ), null, null, null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val androidContactId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                    )
                )
                val phoneNumber = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                )
                val db = AppDatabase.getDatabase(applicationContext)
                val contactDao = db.contactDao()
                val contactId = contactDao.getIdByAndroidId(androidContactId)
                    ?: contactDao.insert(
                        Contact(
                            androidContactId = androidContactId,
                            displayName = "",
                            phoneNumber = phoneNumber
                        )
                    ).toInt()
                val conv = Conversation(
                    contactId = contactId,
                    lastMessage = "",
                    timestamp = System.currentTimeMillis()
                )
                db.conversationDao().insert(conv)
            }
        }
    }
}
