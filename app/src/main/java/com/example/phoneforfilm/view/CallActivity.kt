package com.example.phoneforfilm.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.viewmodel.CallViewModel

class CallActivity : AppCompatActivity() {

    private val viewModel: CallViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        val btnEndCall = findViewById<Button>(R.id.btnEndCall)
        val callTimer = findViewById<Chronometer>(R.id.callTimer)

        callTimer.start()

        viewModel.registerProximitySensor(this) { isNear ->
            if (isNear) {
                window.decorView.post {
                    window.decorView.alpha = 0f
                }
            } else {
                window.decorView.post {
                    window.decorView.alpha = 1f
                }
            }
        }

        btnEndCall.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterProximitySensor()
    }
}
