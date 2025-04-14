
package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R

class IncomingCallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_call)

        val btnAnswer: Button = findViewById(R.id.btnAnswer)
        val btnDecline: Button = findViewById(R.id.btnDecline)

        btnAnswer.setOnClickListener {
            startActivity(Intent(this, CallActivity::class.java))
            finish()
        }

        btnDecline.setOnClickListener {
            finish()
        }
    }
}
