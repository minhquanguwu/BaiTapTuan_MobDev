package com.example.slide11

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.Toast

class GetTime : AppCompatActivity() {
    private var isBound = false
    private var timeService: TimeService? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            val binder = iBinder as TimeService.TimeBinder
            timeService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            isBound = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_time)

        val btn = findViewById<Button>(R.id.btnTime1)
        btn.setOnClickListener {
            showCurrentTime(it)
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, TimeService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }

    fun showCurrentTime(view: View) {
        if (isBound) {
            val currentTime = timeService?.getCurrentTime()
            Toast.makeText(this, "Thời gian hiện tại: $currentTime", Toast.LENGTH_SHORT).show()
        }
    }
}