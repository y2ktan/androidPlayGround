package com.ressphere.architectureexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val repository = NoteRepo(application)
    val allNotes = repository.allNotes

    fun insert(note:Note){
        repository.insert(note)
    }

    fun update(note:Note){
        repository.update(note)
    }

    fun delete(note:Note){
        repository.delete(note)
    }

    fun deleteAll(){
        repository.deleteAll()
    }


}