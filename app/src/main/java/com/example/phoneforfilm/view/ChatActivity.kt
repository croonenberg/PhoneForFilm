package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.R
import com.example.phoneforfilm.adapter.MessageAdapter
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.repository.ConversationRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    @Inject lateinit var convRepo: ConversationRepository
    private lateinit var binding: ActivityChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private var chatId: Int = -1
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatId = intent.getIntExtra("chatId", -1)

        // Load theme and apply
        MainScope().launch {
            val conv = convRepo.getById(chatId)
            applyTheme(conv.theme)
        }

        // ... existing code ...
    }

    private fun applyTheme(theme: String) {
        when(theme) {
            "Greenroom" -> binding.root.setBackgroundColor(getColor(R.color.greenroomBackground))
            "Blue Stage" -> binding.root.setBackgroundColor(getColor(R.color.bluestageBackground))
            "Grey Card" -> binding.root.setBackgroundColor(getColor(R.color.greycardBackground))
            "Neutral Light" -> binding.root.setBackgroundColor(getColor(R.color.neutralBackground))
            "Darkroom" -> binding.root.setBackgroundColor(getColor(R.color.darkroomBackground))
        }
    }

    fun onChangeTheme() {
        val themes = arrayOf("Greenroom","Blue Stage","Grey Card","Neutral Light","Darkroom")
        AlertDialog.Builder(this)
            .setTitle("Select Theme")
            .setItems(themes) { _, which ->
                val newTheme = themes[which]
                // save to DB
                MainScope().launch {
                    val conv = convRepo.getById(chatId)
                    convRepo.update(conv.copy(theme = newTheme))
                    applyTheme(newTheme)
                }
            }.show()
    }
}
