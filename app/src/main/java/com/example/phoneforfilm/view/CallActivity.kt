
package com.example.phoneforfilm.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R

class CallActivity : AppCompatActivity() {

    private lateinit var callTimer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        callTimer = findViewById(R.id.callTimer)

        Handler(Looper.getMainLooper()).postDelayed({
            callTimer.start()
        }, 1000)
    }
}
