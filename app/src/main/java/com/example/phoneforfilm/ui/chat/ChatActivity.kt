package com.example.phoneforfilm.ui.chat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.ui.common.UIState
import com.example.phoneforfilm.utils.ThemeMapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply theme if already selected for this conversation
        val conversationId = intent.getLongExtra(EXTRA_CONVERSATION_ID, -1L)
        viewModel.loadTheme(conversationId)

        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe theme updates
        lifecycleScope.launch {
            viewModel.themeState.collectLatest { state ->
                if (state is UIState.Success) {
                    setTheme(ThemeMapper.getStyleRes(state.data))
                }
            }
        }
    }

    companion object {
        const val EXTRA_CONVERSATION_ID = "conversation_id"
    }
}
