package com.example.phoneforfilm.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.viewmodel.CallViewModel

class CallActivity : AppCompatActivity() {

    private lateinit var blackOverlay: View
    private val viewModel: CallViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        val endCallButton = findViewById<Button>(R.id.btnEndCall)
        val chronometer = findViewById<Chronometer>(R.id.callChronometer)
        blackOverlay = findViewById(R.id.blackOverlay)

        chronometer.start()

        endCallButton.setOnClickListener {
            finish()
        }

        viewModel.registerProximitySensor(this) { isNear ->
            blackOverlay.visibility = if (isNear) View.VISIBLE else View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.unregisterProximitySensor()
    }
}
