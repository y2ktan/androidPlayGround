package com.ressphere.architectureexample

import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoteRepo(private val application:Application) {
    private var noteDao:NoteDao? = null
    var allNotes:LiveData<List<Note>>? = null
        private set

    init {
        val noteDatabase = NoteDatabase.getAppDataBase(application)
        noteDao = noteDatabase?.noteDao()
        allNotes = noteDao?.getAllNotes()
    }

    fun insert(note:Note){
        Observable.fromCallable {
            with(noteDao){
                this?.insert(note)
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun update(note:Note){
        Observable.fromCallable {
            with(noteDao){
                this?.update(note)
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun delete(note:Note){
        Observable.fromCallable {
            with(noteDao){
                this?.delete(note)
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun deleteAll(){
        Observable.fromCallable {
            with(noteDao){
                this?.deleteAllNotes()
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }







}