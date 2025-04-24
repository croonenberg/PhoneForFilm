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
 * Conversation‑overview screen.
 * - Lists all conversations with associated contact names.
 * - FAB lets the user create / open a conversation for a picked contact.
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

        // id in layout = fabNewConversation
        binding.fabNewConversation.setOnClickListener { pickContact.launch(null) }

        populateList()
    }

    private fun populateList() {
        lifecycleScope.launch(Dispatchers.IO) {
            val conversations = db.conversationDao().getAllNow()
            val contacts = db.contactDao().getAllNow().associate { it.id to it.name }

            withContext(Dispatchers.Main) {
                binding.rvConversations.adapter =
                    ConversationAdapter(conversations, contacts) { conversation ->
                        val intent = Intent(this@ChatListActivity, ChatActivity::class.java)
                        intent.putExtra("CONVERSATION_ID", conversation.id)
                        startActivity(intent)
                    }
            }
        }
    }

    /**
     * Ensure there is a Conversation row for the picked contact and open it.
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
                    val contactId = cursor.getLong(0)
                    val displayName = cursor.getString(1) ?: ""

                    // try to get first phone‑number
                    var phoneNumber = ""
                    contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                        "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID}=?",
                        arrayOf(contactId.toString()),
                        null
                    )?.use { pCur ->
                        if (pCur.moveToFirst()) {
                            phoneNumber = pCur.getString(0)
                        }
                    }

                    // ensure contact exists in local DB
                    var contactDbId = db.contactDao().getIdByAndroidId(contactId)
                    if (contactDbId == null) {
                        contactDbId = db.contactDao().insert(
                            Contact(
                                androidContactId = contactId,
                                name = displayName,
                                phoneNumber = phoneNumber
                            )
                        ).toInt()
                    }

                    // ensure conversation exists
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
                        intent.putExtra("CONVERSATION_ID", convId)
                        startActivity(intent)
                    }

                    // refresh the list so the new / updated convo shows up immediately
                    populateList()
                }
            }
        }
    }
}