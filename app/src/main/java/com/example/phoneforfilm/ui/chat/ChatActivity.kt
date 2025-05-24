package com.example.phoneforfilm.ui.chat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.phoneforfilm.ui.common.UIState
import com.example.phoneforfilm.utils.ThemeMapper

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private val adapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        binding.recyclerViewMessages.adapter = adapter

        binding.toolbar.setOnLongClickListener {
            showThemeDialog()
            true
        }

        lifecycleScope.launch {
            viewModel.themeState.collectLatest { state ->
                if (state is UIState.Success) {
                    setTheme(ThemeMapper.getStyleRes(state.data))
                }
            }
        }
    }

    private fun showThemeDialog() {
        // TODO: implement dialog for selecting theme
    }
}