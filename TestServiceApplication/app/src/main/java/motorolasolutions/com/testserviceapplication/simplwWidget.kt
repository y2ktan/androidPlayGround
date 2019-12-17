package motorolasolutions.com.testserviceapplication

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.RemoteViews

class simpleWidget: AppWidgetProvider(){
    private val myBroadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            context?.startActivity( Intent(context, Main2Activity::class.java))
        }
    }

    private val filter = IntentFilter("START_MAIN2ACTIVITY")

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        for(appWidgetId in appWidgetIds!!){
            val intent = Intent(context, Main2Activity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent,0)
            val remoteViews = RemoteViews(context!!.packageName, R.layout.simple_widget)
            remoteViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent)
            appWidgetManager!!.updateAppWidget(appWidgetId, remoteViews)
        }
    }

//    override fun onDisabled(context: Context?) {
//        context?.unregisterReceiver(myBroadcastReceiver)
//    }
//
//    override fun onEnabled(context: Context?) {
//        context?.registerReceiver(myBroadcastReceiver, filter)
//    }
}