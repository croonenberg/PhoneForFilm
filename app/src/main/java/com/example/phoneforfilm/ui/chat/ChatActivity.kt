package com.example.phoneforfilm.ui.chat

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // fetch id via new or legacy key
        val contactId = intent.getLongExtra(EXTRA_CONTACT_ID,
            intent.getLongExtra(EXTRA_CONVERSATION_ID, -1L))

        if (contactId == -1L) {
            Toast.makeText(this, "No conversation selected", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: load conversation based on contactId
        observeMessages()
    }

    private fun observeMessages() {
        // existing logic
    }

    companion object {
        /** Preferred key */
        const val EXTRA_CONTACT_ID = "contactId"
        /** Legacy alias for back‑compat */
        const val EXTRA_CONVERSATION_ID = EXTRA_CONTACT_ID
    }
}
