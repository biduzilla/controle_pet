package com.ricky.controle_pet

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ControlePetApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val notificationChannel = NotificationChannel(
            "pet_reminder",
            "Pet reminder channel",
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationChannel.description = "A notification channel for pet reminders"

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}