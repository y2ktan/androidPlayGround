package motorolasolutions.com.testserviceapplication

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class MyService : Service() {
    private val myBroadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            context?.startActivity( Intent(context, Main2Activity::class.java))
        }
    }

    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter("START_MAIN2ACTIVITY")
        registerReceiver(myBroadcastReceiver, filter)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val intent = Intent( "START_MAIN2ACTIVITY")
        sendBroadcast(intent)
        return START_NOT_STICKY
    }
}
