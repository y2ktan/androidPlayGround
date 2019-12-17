package motorolasolutions.com.testserviceapplication

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.content.Context.POWER_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.os.PowerManager
import android.view.Display
import android.content.Context.DISPLAY_SERVICE
import android.hardware.display.DisplayManager
import android.os.Build
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



interface ActivityStateListener{
    fun onActivitiesInBackground()
}

class App : Application(), Application.ActivityLifecycleCallbacks {
    private var activityReferences = 0 @Synchronized get @Synchronized set
    private var isActivityChangingConfigurations = false @Synchronized get @Synchronized set


    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }
    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {

    }

    override fun onActivityStarted(activity: Activity?) {
        if (++activityReferences == 1 && !isActivityChangingConfigurations) {
            // App enters foreground
            Log.i("vcfr67", "in foreground")
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity?) {
        if (activity != null) {
            isActivityChangingConfigurations = activity.isChangingConfigurations
        }
        if (--activityReferences == 0 && !isActivityChangingConfigurations) {
            if (activity != null) {
                if(isScreenOn(activity.applicationContext)) {
                    Log.i("vcfr67", "in background")
                    for(listener in activityStateListeners){
                        listener.onActivitiesInBackground()
                    }
                    activityStateListeners.clear()
                }
            }

        }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }

    fun isScreenOn(context: Context): Boolean {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return pm.isInteractive
    }
    private fun isScreenOff(): Boolean {

        val myProcess = ActivityManager.RunningAppProcessInfo()
        ActivityManager.getMyMemoryState(myProcess)
        Log.i("vcfr67", "myProcess.importance: ${myProcess.importance}")
        return myProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_TOP_SLEEPING_PRE_28
    }

    companion object {
        val activityStateListeners:ArrayList<ActivityStateListener> = ArrayList()
    }
}