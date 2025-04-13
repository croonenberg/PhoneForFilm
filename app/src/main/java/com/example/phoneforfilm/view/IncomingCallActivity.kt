package com.example.phoneforfilm.view

import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R

class IncomingCallActivity : AppCompatActivity() {

    private var ringtone: Ringtone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_call)

        val btnAnswer = findViewById<Button>(R.id.btnAnswer)
        val btnDecline = findViewById<Button>(R.id.btnDecline)

        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        ringtone = RingtoneManager.getRingtone(applicationContext, uri)
        ringtone?.play()

        btnAnswer.setOnClickListener {
            ringtone?.stop()
            startActivity(Intent(this, CallActivity::class.java))
            finish()
        }

        btnDecline.setOnClickListener {
            ringtone?.stop()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ringtone?.stop()
    }
}
