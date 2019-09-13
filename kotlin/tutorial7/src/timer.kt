package com.motorolasolutions.radiocontrol.ui.appwidgets.ptt.indicator

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RemoteViews
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

private var blinkingTimer: Job? = null

data class BlinkingWidgetModel(val blinkingIconRemoteViews: ConcurrentHashMap<Int, RemoteViews> = ConcurrentHashMap())

val blinkingWidgetModel = BlinkingWidgetModel()

private fun startCoroutineTimer(delayMillis: Long = 0, repeatMillis: Long = 0, action: () -> Unit) = GlobalScope.launch {
    delay(delayMillis)
    if (repeatMillis > 0) {
        while (true) {
            action()
            delay(repeatMillis)
        }
    } else {
        action()
    }
}

val startBlinking = {
    if (blinkingTimer == null ) {
        if(blinkingWidgetModel.blinkingIconRemoteViews.size > 0) {
            blinkingTimer = startCoroutineTimer(delayMillis = 500L, repeatMillis = 500L) {
                //PttWidgetUpdater.getInstance().updateBlinkingWidget()
            }
        }
    }
}


val stopBlinking = {
    blinkingTimer?.cancel().also { blinkingTimer = null }
    blinkingWidgetModel.blinkingIconRemoteViews.clear()
}