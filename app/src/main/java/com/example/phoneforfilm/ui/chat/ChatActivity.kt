package com.example.phoneforfilm.ui.chat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.phoneforfilm.R
import com.example.phoneforfilm.ui.common.UIState
import com.example.phoneforfilm.utils.ThemeUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.firstOrNull

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private val viewModel: ChatViewModel by viewModels()
    private val conversationId: Long by lazy { intent.getLongExtra("conversationId", 0L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Observe theme synchronously before setting content
        val themeKey: String? = runBlocking {
            viewModel.themeState.firstOrNull()?.let { state ->
                (state as? UIState.Success)?.data
            }
        }
        setTheme(ThemeUtils.getStyleRes(themeKey))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initUi()
    }

    private fun initUi() {
        val root = findViewById<View>(R.id.chat_root)
        root.setOnLongClickListener {
            showThemeSelectionDialog()
            true
        }
    }

    private fun showThemeSelectionDialog() {
        val themeKeys = arrayOf("greenroom", "bluestage", "default")
        val themeNames = arrayOf("Greenroom", "Bluestage", "Reset naar standaard")
        AlertDialog.Builder(this)
            .setTitle("Kies thema")
            .setItems(themeNames) { _, which ->
                val key = themeKeys[which]
                viewModel.applyTheme(conversationId, key)
                recreate()
            }
            .show()
    }
}
