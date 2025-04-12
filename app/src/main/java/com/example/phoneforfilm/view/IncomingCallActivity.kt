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

        val answerButton = findViewById<Button>(R.id.btnAnswer)
        val declineButton = findViewById<Button>(R.id.btnDecline)

        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        ringtone = RingtoneManager.getRingtone(applicationContext, notification)
        ringtone?.play()

        answerButton.setOnClickListener {
            ringtone?.stop()
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
            finish()
        }

        declineButton.setOnClickListener {
            ringtone?.stop()
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        ringtone?.stop()
    }
}
