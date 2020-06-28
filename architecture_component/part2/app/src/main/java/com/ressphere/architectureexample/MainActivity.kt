package com.ressphere.architectureexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode

import android.os.StrictMode.VmPolicy
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel:NoteViewModel

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

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java).apply {
            val noteAdapter = NoteAdapter()
            recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler_view.setHasFixedSize(true)
            recycler_view.adapter = noteAdapter
            allNotes?.observe(this@MainActivity, Observer {
                noteAdapter.setNotes(it)
            })
        }

        button_add_note.setOnClickListener(){
            val intent = Intent(this, AddNoteActivity::class.java).apply {
                startActivityForResult(this, ADD_NOTE_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK){
            data?.let {
                val title = it.getStringExtra(AddNoteActivity.EXTRA_TITLE)
                val description =  it.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
                val priority = it.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 0)

                noteViewModel.insert(Note(title = title, description = description, priority = priority))
                Toast.makeText(applicationContext, "Note saved", Toast.LENGTH_LONG).show()
            } ?: Toast.makeText(applicationContext, "Note saved", Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(applicationContext, "Note saved", Toast.LENGTH_LONG).show()
        }
    }

    companion object{
        private const val ADD_NOTE_REQUEST = 1
    }
}