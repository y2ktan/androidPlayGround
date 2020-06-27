package com.ressphere.architectureexample

import android.os.Bundle
import android.os.StrictMode

import android.os.StrictMode.VmPolicy
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork() // or .detectAll() for all detectable problems
                .penaltyLog()
                .build())
        StrictMode.setVmPolicy(VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes?.observe(this, Observer {
            Toast.makeText(applicationContext, "all notes is found", Toast.LENGTH_LONG).show()
        })
    }
}