package motorolasolutions.com.testserviceapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        context?.startActivity( Intent(context, Main2Activity::class.java))
    }
}
