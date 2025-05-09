package com.example.app.ui.chat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.app.R
import com.example.app.utils.ThemeMapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity() {
    private lateinit var viewModel: ChatViewModel
    private val conversationId: Long by lazy { intent.getLongExtra("conversationId", 0L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewModel.loadTheme(conversationId)
            val themeKey = viewModel.themeFlow.first()
            setTheme(ThemeMapper.getStyleRes(themeKey))
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_chat)
            initUi()
        }
    }

    private fun initUi() {
        val root = findViewById<View>(R.id.chat_root)
        root.setOnLongClickListener {
            showThemeSelectionDialog()
            true
        }
    }

    private fun showThemeSelectionDialog() {
        val themeKeys = arrayOf("greenroom", "bluestage")
        val themeNames = arrayOf("Greenroom", "Bluestage")
        AlertDialog.Builder(this)
            .setTitle("Kies thema")
            .setItems(themeNames) { _, which ->
                val key = themeKeys[which]
                viewModel.setTheme(conversationId, key)
                recreate()
            }
            .show()
    }
}
