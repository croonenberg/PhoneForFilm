
package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.phoneforfilm.R
import com.example.phoneforfilm.databinding.ActivityMainBinding
import com.example.phoneforfilm.utils.ThemeManager
import com.example.phoneforfilm.view.ChatListActivity

/**
 * Home screen with quick‑action buttons.
 *
 *  • Start Chat → opens the list of conversations
 *  • Start Call → existing demo functionality
 *  • Theme & Language selectors
 */
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // apply the currently selected theme before setContentView
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*──────────────────────────────────────────
         * Navigation
         *─────────────────────────────────────────*/
        binding.btnStartChat.setOnClickListener {
            startActivity(Intent(this, ChatListActivity::class.java))
        }

        binding.btnStartCallMain.setOnClickListener {
            startActivity(Intent(this, CallActivity::class.java))
        }

        /*──────────────────────────────────────────
         * Theme picker
         *─────────────────────────────────────────*/
        binding.btnTheme.setOnClickListener {
            val themes = arrayOf("Classic", "Dark", "Ocean")
            AlertDialog.Builder(this)
                .setTitle(R.string.choose_theme)
                .setItems(themes) { _, which ->
                    ThemeManager.setTheme(this, themes[which])
                    recreate()
                }

                .show()
        }

        /*──────────────────────────────────────────
         * Language picker
         *─────────────────────────────────────────*/
        binding.btnLanguage.setOnClickListener {
            startActivity(Intent(this, LanguageSelectionActivity::class.java))
        }
    }
}