package com.example.phoneforfilm.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private var conversationId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        conversationId = intent.getLongExtra("conversationId", -1L)
        viewModel.loadMessages(conversationId)
    }

    /** Open the edit UI for this message. */
    fun onEditMessage(msg: Message) {
        // TODO: show edit dialog or start EditConversationActivity
    }

    /** Delete this message from DB and refresh list. */
    fun onDeleteMessage(msg: Message) {
        viewModel.deleteMessage(msg)
        viewModel.loadMessages(conversationId)
    }

    /** Copy message text to clipboard. */
    fun onCopyMessage(msg: Message) {
        val cb = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        cb.setPrimaryClip(ClipData.newPlainText("message", msg.text))
        Toast.makeText(this, getString(R.string.copied_to_clipboard), Toast.LENGTH_SHORT).show()
    }

    /** Live theme chooser for this chat. */
    fun onChangeTheme() {
        val themeNames = arrayOf(
            getString(R.string.theme_greenroom),
            getString(R.string.theme_blue_stage),
            getString(R.string.theme_grey_card),
            getString(R.string.theme_neutral_light),
            getString(R.string.theme_darkroom)
        )
        val themeValues = listOf("Greenroom", "Blue Stage", "Grey Card", "Neutral Light", "Darkroom")
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.choose_theme))
            .setItems(themeNames) { _, which ->
                viewModel.setThemeForConversation(conversationId, themeValues[which])
            }
            .show()
    }

    /** Live language chooser for this chat. */
    fun onChangeLanguage() {
        val langNames = arrayOf(
            getString(R.string.language_dutch),
            getString(R.string.language_english),
            getString(R.string.language_german),
            getString(R.string.language_french),
            getString(R.string.language_spanish)
        )
        val langCodes = arrayOf("nl", "en", "de", "fr", "es")
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.choose_language))
            .setItems(langNames) { _, which ->
                viewModel.setLanguageForConversation(conversationId, langCodes[which])
                recreate()
            }
            .show()
    }

    /** Toggle sender/receiver role of this message. */
    fun onToggleSender(msg: Message) {
        msg.isSender = !msg.isSender
        viewModel.updateMessage(msg)
        viewModel.loadMessages(conversationId)
    }
}
