package com.example.slide11

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimeService : Service() {
    inner class TimeBinder : Binder() {
        fun getService(): TimeService {
            return this@TimeService
        }
    }

    private val binder = TimeBinder()

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

}