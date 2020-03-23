package com.example.ressphere

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.webkit.PermissionRequest
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.security.Permission
import java.util.ArrayList


class GenericFileProvider : FileProvider()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val permissions = ArrayList<String>()
        requestExternalStoragePermission(this, permissions = permissions)
        requestInstallPackagesPermission(this, permissions = permissions)

        if (permissions.size > 0) {
            val permissionsInArray = arrayOfNulls<String>(permissions.size)
            permissions.toArray(permissionsInArray)
            ActivityCompat.requestPermissions(
                this,
                permissionsInArray,
                REQUEST_PERMISSION
            )
        }

        btnUpdate.setOnClickListener{
            val path = "/sdcard/FireApp-debug_update.apk"
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val apkFileUri = FileProvider.getUriForFile(applicationContext,
                "$packageName.provider", File(path))
            i.setDataAndType(apkFileUri, "application/vnd.android.package-archive")
            Log.d(TAG, "packageName: $packageName")
            Log.d(TAG, "About to install new .apk")
            startActivity(i)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestExternalStoragePermission(activity: Activity, permissions:ArrayList<String>){
        val hasPermission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if(hasPermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun requestInstallPackagesPermission(activity: Activity, permissions:ArrayList<String>){
        val hasPermission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.INSTALL_PACKAGES)
        if(hasPermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(android.Manifest.permission.INSTALL_PACKAGES)
        }
    }

    companion object{
        private const val TAG = "AutoUpdate"
        private const val REQUEST_PERMISSION = 46
    }
}
