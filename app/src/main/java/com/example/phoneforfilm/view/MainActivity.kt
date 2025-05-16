package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.phoneforfilm.R

+        // Start chat-overzicht
+        binding.buttonStartChat.setOnClickListener {
    +            startActivity(Intent(this, ChatListActivity::class.java))
    +        }
+
+        // Start een telefoongesprek (buttonStartCall bestaat al)
+        binding.buttonStartCall.setOnClickListener {
    +            startActivity(Intent(this, CallActivity::class.java))
    +        }
+
+        // Toon contactlijst via bestaande knopViewSubtitles
+        binding.buttonViewSubtitles.setOnClickListener {
    +            startActivity(Intent(this, ContactListActivity::class.java))
    +        }
+
+        // Open thema-instellingen via bestaande buttonSubmitSubtitle
+        binding.buttonSubmitSubtitle.setOnClickListener {
    +            startActivity(Intent(this, ThemeSettingsActivity::class.java))
    +        }
}
}