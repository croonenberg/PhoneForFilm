package com.example.phoneforfilm.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.adapter.ConversationAdapter
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.databinding.ActivityChatListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Lists all conversations and lets the user start a new one
 * by picking a contact from the address‑book.
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
        binding.fabNewConversation.setOnClickListener { pickContact.launch(null) }

        loadConversations()
    }

    private fun loadConversations() {
        lifecycleScope.launch(Dispatchers.IO) {
            val conversations = db.conversationDao().getAllNow()
            val contacts = db.contactDao().getAllNow().associate { it.id to it.name }
            withContext(Dispatchers.Main) {
                binding.rvConversations.adapter =
                    ConversationAdapter(conversations, contacts) { conversation ->
                        openChat(conversation.id)
                    }
            }
        }
    }

    private fun openChat(conversationId: Int) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("CONVERSATION_ID", conversationId)
        startActivity(intent)
    }

    /**
     * Ensures we have a local Contact row and Conversation row for the selected Android contact.
     * Then opens the chat.
     */
    private fun handlePickedContact(uri: Uri) {
        lifecycleScope.launch(Dispatchers.IO) {
            // Get contact id & name
            var contactId: Long = -1
            var displayName = ""
            contentResolver.query(
                uri,
                arrayOf(
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                ),
                null,
                null,
                null
            )?.use { cur ->
                if (cur.moveToFirst()) {
                    contactId = cur.getLong(0)
                    displayName = cur.getString(1) ?: ""
                }
            }

            if (contactId == -1L) return@launch // no valid result

            // first phone number (optional)
            var phoneNumber = ""
            contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID}=?",
                arrayOf(contactId.toString()),
                null
            )?.use { pCur ->
                if (pCur.moveToFirst()) phoneNumber = pCur.getString(0)
            }

            // ensure local contact
            var localId = db.contactDao().getIdByAndroidId(contactId)
            if (localId == null) {
                localId = db.contactDao().insert(
                    Contact(
                        androidContactId = contactId,
                        name = displayName,
                        phoneNumber = phoneNumber
                    )
                ).toInt()
            }

            // ensure conversation
            var convId = db.conversationDao().getIdByContact(localId)
            if (convId == null) {
                convId = db.conversationDao().insert(
                    Conversation(
                        contactId = localId,
                        lastMessage = "",
                        timestamp = System.currentTimeMillis()
                    )
                ).toInt()
            }

            withContext(Dispatchers.Main) {
                openChat(convId)
                loadConversations() // refresh list
            }
        }
    }
}