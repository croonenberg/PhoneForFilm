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
 * Shows a RecyclerView with one‑line previews of each conversation.  The user can tap
 * the FAB to create a new conversation by selecting someone from the Android address book.
 */
class ChatListActivity : BaseActivity() {

    private lateinit var binding: ActivityChatListBinding
    private val db by lazy { AppDatabase.getDatabase(this) }

    private val pickContact =
        registerForActivityResult(ActivityResultContracts.PickContact()) { uri: Uri? ->
            uri?.let { handlePickedContact(it) }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvConversations.layoutManager = LinearLayoutManager(this)
        binding.fabNewConversation.setOnClickListener { pickContact.launch(null) }
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
            val adapter =
                ConversationAdapter(conversations, contactNameMap) { conversation ->
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
            contentResolver.query(
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
            )?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val androidId = cursor.getLong(0)
                    val name = cursor.getString(1) ?: "Onbekend"
                    val number = cursor.getString(2) ?: ""
                    val photo = cursor.getString(3)

                    // ---------- Ensure a Contact row exists ----------
                    var contactDbId: Int? = db.contactDao().getIdByAndroidId(androidId)
                    if (contactDbId == null) {
                        contactDbId = db.contactDao().insert(
                            Contact(
                                androidContactId = androidId,
                                name = name,
                                phoneNumber = number,
                                photoUri = photo
                            )
                        ).toInt()
                    }

                    // ---------- Ensure a Conversation row exists ----------
                    var convId: Int? = db.conversationDao().getIdByContact(contactDbId)
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
                        intent.putExtra("CONVERSATION_ID", convId!!.toLong())
                        startActivity(intent)
                    }
                }
            }
        }
    }
}