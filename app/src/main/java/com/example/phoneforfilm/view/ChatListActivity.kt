package com.example.phoneforfilm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.data.PhoneForFilmDatabase
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import com.example.phoneforfilm.adapter.ConversationAdapter

class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding
    private lateinit var adapter: ConversationAdapter

    companion object {
        private const val REQUEST_CONTACT_PICK = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ConversationAdapter(emptyList(), emptyMap()) { conversation ->
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra("conversationId", conversation.id)
            }
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.fabNewChat.setOnClickListener {
            val pickIntent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            startActivityForResult(pickIntent, REQUEST_CONTACT_PICK)
        }

        val db = PhoneForFilmDatabase.getInstance(applicationContext)
        val conversationDao = db.conversationDao()
        val contactDao = db.contactDao()

        conversationDao.getAllWithContact().observe(this, Observer { list ->
            val convs = list.map { it.conversation }
            val names = list.associate { it.conversation.contactId to it.contact.displayName }
            adapter.updateData(convs, names)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CONTACT_PICK && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val cursor = contentResolver.query(uri,
                    arrayOf(ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                           ContactsContract.CommonDataKinds.Phone.NUMBER),
                    null, null, null)
                cursor?.use {
                    if (it.moveToFirst()) {
                        val androidContactId = it.getLong(
                            it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                        )
                        val phoneNumber = it.getString(
                            it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        )
                        val db = PhoneForFilmDatabase.getInstance(applicationContext)
                        val newContactId = db.contactDao().upsertAndroidContact(androidContactId, phoneNumber)
                        val conv = Conversation(contactId = newContactId, lastMessage = "", timestamp = System.currentTimeMillis())
                        db.conversationDao().insert(conv)
                    }
                }
            }
        }
    }
}
