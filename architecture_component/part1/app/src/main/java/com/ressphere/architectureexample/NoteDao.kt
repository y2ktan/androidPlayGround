package com.ressphere.architectureexample

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(note:Note)

    @Update
    fun update(note:Note)

    @Delete
    fun delete(note:Note)

    @Query("DELETE From note_table")
    fun deleteAllNotes()

    @Query("SELECT * from NOTE_TABLE ORDER BY priority_column DESC")
    fun getAllNotes():LiveData<List<Note>>

}