package com.karexpert.navialifecareassignment.core

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.karexpert.navialifecareassignment.ui.MainActivity
import com.karexpert.navialifecareassignment.R
import com.karexpert.navialifecareassignment.extension.vectorToBitmap

class NotificationUtils(base:Context,mealName : String) : ContextWrapper(base) {
    val MYCHANNEL_ID = "App Alert Notification ID"
    val MYCHANNEL_NAME = "App Alert Notification"
    var mealName1 =""

    private var manager: NotificationManager? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
            mealName1=mealName
        }
    }

    // Create channel for Android version 26+
    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel = NotificationChannel(MYCHANNEL_ID, MYCHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel.enableVibration(true)

        getManager().createNotificationChannel(channel)
    }

    // Get Manager
    fun getManager() : NotificationManager {
        if (manager == null) manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager as NotificationManager
    }

    fun getNotificationBuilder(): NotificationCompat.Builder {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val bitmap = applicationContext.vectorToBitmap(R.drawable.ic_schedule_black_24dp)

//        val pendingIntent = PendingIntent.getActivity(this, System.currentTimeMillis().toInt(), intent, 0)
        return NotificationCompat.Builder(applicationContext, MYCHANNEL_ID)
                .setContentTitle("Its Meal Time")
                .setContentText(mealName1)
                .setLargeIcon(bitmap)
                .setSmallIcon(R.drawable.ic_schedule_black_24dp)
                .setColor(Color.YELLOW)
//                .setContentIntent(pendingIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
    }
}