
package com.example.phoneforfilm.view

import android.app.Activity
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
 * Displays all conversations and allows the user to start a new one by picking
 * a contact from the device address‑book.
 */
class ChatListActivity : BaseActivity() {

    private lateinit var binding: ActivityChatListBinding
    private lateinit var db: AppDatabase

    private val pickContact =
        registerForActivityResult(ActivityResultContracts.PickContact()) { uri: Uri? ->
            uri?.let { handlePickedContact(it) }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        binding.rvConversations.layoutManager = LinearLayoutManager(this)

        /*──────────────────────────────────────────
         * FAB – start new conversation
         *─────────────────────────────────────────*/
        binding.fabNewConversation.setOnClickListener {
            pickContact.launch(null)
        }
    }

    override fun onResume() {
        super.onResume()
        loadConversations()
    }

    private fun loadConversations() {
        lifecycleScope.launch {
            val conversations: List<Conversation> = withContext(Dispatchers.IO) {
                db.conversationDao().getAllNow()
            }
            val contacts = withContext(Dispatchers.IO) {
                db.contactDao().getAllNow()
            }
            val contactNameMap = contacts.associateBy({ it.id }, { it.name })
            val adapter = ConversationAdapter(conversations, contactNameMap) { conversation ->
                val intent = Intent(this@ChatListActivity, ChatActivity::class.java)
                intent.putExtra("CONVERSATION_ID", conversation.id.toLong())
                startActivity(intent)
            }
            binding.rvConversations.adapter = adapter
        }
    }

    /**
     * After the user chose a person from the address book we either open an existing
     * conversation or create a fresh one.
     */
    private fun handlePickedContact(uri: Uri) {
        lifecycleScope.launch(Dispatchers.IO) {
            val cursor = contentResolver.query(
                uri,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.Contacts.PHOTO_URI
                ),
                null,
                null,
                null
            )
            cursor?.use {
                if (it.moveToFirst()) {
                    val id = it.getLong(0)
                    val name = it.getString(1) ?: "Onbekend"
                    val number = it.getString(2) ?: ""
                    val photo = it.getString(3)

                    // make sure we have a local Contact row
                    var contactDbId = db.contactDao().getIdByAndroidId(id)
                    if (contactDbId == null) {
                        contactDbId = db.contactDao().insert(
                            Contact(
                                androidContactId = id,
                                name = name,
                                phoneNumber = number,
                                photoUri = photo
                            )
                        ).toInt()
                    }

                    // look for existing conversation
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
