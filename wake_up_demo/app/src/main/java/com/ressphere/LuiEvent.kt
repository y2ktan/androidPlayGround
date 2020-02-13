package com.ressphere

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LuiEvent : BroadcastReceiver() {
    // from terminal type: adb shell am broadcast -n com.ressphere/.LuiEvent
    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity(Intent(context, Alarm::class.java))
    }
}
