package com.example.phoneforfilm.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.lifecycleScope
import com.example.phoneforfilm.adapter.ConversationAdapter
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Shows the list of conversations. When the user presses the floating action‑button he can
 * pick a contact from the address‑book.  If a conversation with that contact already exists
 * we jump straight into it, otherwise we create a fresh Conversation row first.
 */
class ChatListActivity : BaseActivity() {

    private lateinit var binding: ActivityChatListBinding
    private val db by lazy { AppDatabase.getDatabase(this) }

    private val pickContact = registerForActivityResult(
        ActivityResultContracts.PickContact()
    ) { uri: Uri? ->
        uri?.let { handlePickedContact(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvConversations.layoutManager = LinearLayoutManager(this)

        binding.fabNewChat.setOnClickListener { pickContact.launch(null) }

        lifecycleScope.launch(Dispatchers.IO) {
            val conversations = db.conversationDao().getAllWithContact()
            withContext(Dispatchers.Main) {
                binding.rvConversations.adapter =
                    ConversationAdapter(conversations) { convId ->
                        val intent = Intent(this@ChatListActivity, ChatActivity::class.java)
                        intent.putExtra("CONVERSATION_ID", convId)
                        startActivity(intent)
                    }
            }
        }
    }

    /**
     * Reads the contact the user chose and makes sure there is a Conversation row for it.
     * The ACTION_PICK result‑uri might point to either Contacts or Data, therefore we treat the
     * returned cursor as opaque and simply request _ID & DISPLAY_NAME which are guaranteed to be
     * present on both tables.
     */
    private fun handlePickedContact(uri: Uri) {
        lifecycleScope.launch(Dispatchers.IO) {

            contentResolver.query(
                uri,
                arrayOf(
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                ),
                null,
                null,
                null
            )?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val contactId =
                        cursor.getLong(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                    val displayName =
                        cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                            )
                        )

                    // ensure contact is stored locally
                    var contactDbId = db.contactDao().getIdByAndroidContactId(contactId)
                    if (contactDbId == null) {
                        contactDbId = db.contactDao().insert(
                            Contact(
                                androidContactId = contactId,
                                name = displayName
                            )
                        ).toInt()
                    }

                    var convId = db.conversationDao().getIdByContact(contactDbId)
                    if (convId == null) {
                        convId = db.conversationDao().insert(
                            Conversation(
                                contactId = contactDbId,
                                lastMessage = "",
                                timestamp = System.currentTimeMillis()
                            )
                        ).toInt()
                    }

                    withContext(Dispatchers.Main) {
                        val intent = Intent(this@ChatListActivity, ChatActivity::class.java)
                        intent.putExtra("CONVERSATION_ID", convId.toLong())
                        startActivity(intent)
                    }
                }
            }
        }
    }
}