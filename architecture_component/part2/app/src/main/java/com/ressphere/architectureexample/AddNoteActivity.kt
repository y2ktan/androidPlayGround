package com.ressphere.architectureexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        number_picker_priority.minValue =1
        number_picker_priority.maxValue = 10
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.add_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun saveNote(){
        val title = edit_text_title.text
        val description = edit_text_description.text
        val priority = number_picker_priority.value

        if(title.isNullOrEmpty() || description.isNullOrEmpty()){
            Toast.makeText(applicationContext,
                    "Please insert a title and description", Toast.LENGTH_LONG).show()
        } else {
            val data = Intent()
            data.putExtra(EXTRA_TITLE, title.toString())
            data.putExtra(EXTRA_DESCRIPTION, description.toString())
            data.putExtra(EXTRA_PRIORITY, priority)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_TITLE = "com.ressphere.architectureexample.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.ressphere.architectureexample.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.ressphere.architectureexample.EXTRA_PRIORITY"
    }
}
