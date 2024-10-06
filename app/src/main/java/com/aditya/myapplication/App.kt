package com.aditya.myapplication

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          val counterChannel=  NotificationChannel(
                "aditya",
                "aditya",
                NotificationManager.IMPORTANCE_HIGH,
            )

            val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(counterChannel)

        }
    }

}