
package com.example.phoneforfilm.ui.chat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneforfilm.R
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
    private val adapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.inflateMenu(R.menu.menu_chat)
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_theme) {
                showThemeDialog()
                true
            } else false
        }

        setupRecyclerView()
        observeMessages()
        observeTheme()
    }

    private fun setupRecyclerView() = with(binding.recyclerViewMessages) {
        layoutManager = LinearLayoutManager(this@ChatActivity).apply {
            stackFromEnd = true
        }
        adapter = this@ChatActivity.adapter
    }

    private fun observeMessages() {
        lifecycleScope.launch {
            viewModel.messages.collectLatest { list ->
                adapter.submitList(list) {
                    binding.recyclerViewMessages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun observeTheme() {
        lifecycleScope.launch {
            viewModel.themeState.collectLatest { state ->
                if (state is UIState.Success) {
                    setTheme(ThemeMapper.getStyleRes(state.data))
                }
            }
        }
    }

    private fun showThemeDialog() {
        val themes = arrayOf("default", "greenroom", "bluestage")
        AlertDialog.Builder(this)
            .setTitle(R.string.select_theme)
            .setItems(themes) { _, which ->
                viewModel.applyTheme(themes[which])
            }
            .show()
    }
}
