package com.karexpert.navialifecareassignment.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.karexpert.navialifecareassignment.core.NotificationUtils

class MealBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

            val mealName = p1!!.getStringExtra("mealName")

        val notificationUtils = NotificationUtils(context!!, mealName!!)
        val notification = notificationUtils.getNotificationBuilder().build()
        notificationUtils.getManager().notify(System.currentTimeMillis().toInt(), notification)
    }
}