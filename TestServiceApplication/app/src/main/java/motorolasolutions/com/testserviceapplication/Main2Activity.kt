package motorolasolutions.com.testserviceapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), ActivityStateListener {
    override fun onActivitiesInBackground() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action") { finish()}.show()
        }
        App.activityStateListeners.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        App.activityStateListeners.remove(this)
    }

}
