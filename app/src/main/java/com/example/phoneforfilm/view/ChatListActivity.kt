package com.example.phoneforfilm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
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

    companion object {
        private const val REQUEST_CONTACT_PICK = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ConversationAdapter(emptyList(), emptyMap()) { conversation ->
            Intent(this, ChatActivity::class.java).also {
                it.putExtra("conversationId", conversation.id)
                startActivity(it)
            }
        }

        binding.rvConversations.layoutManager = LinearLayoutManager(this)
        binding.rvConversations.adapter       = adapter

        binding.fabNewConversation.setOnClickListener {
            val pickIntent = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            startActivityForResult(pickIntent, REQUEST_CONTACT_PICK)
        }

        val db = AppDatabase.getDatabase(applicationContext)
        val conversationDao = db.conversationDao()
        val contactDao      = db.contactDao()

        // Observe alle gesprekken en vul in met contactnamen
        conversationDao.getAll().observe(this, Observer { convs ->
            // let op: getAllNow() draait in main thread → voor demo hier, maar in productie beter via DAO-join
            val namesMap = contactDao.getAllNow()
                .associate { it.id to it.displayName }
            adapter.updateData(convs, namesMap)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CONTACT_PICK && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
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
                                    displayName = "", // of vul aan via ContactsContract
                                    phoneNumber = phoneNumber
                                )
                            ).toInt()

                        val conv = Conversation(
                            contactId   = contactId,
                            lastMessage = "",
                            timestamp   = System.currentTimeMillis()
                        )
                        db.conversationDao().insert(conv)
                    }
                }
            }
        }
    }
}
