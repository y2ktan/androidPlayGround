package com.ressphere.architectureexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao():NoteDao

    companion object {
        var INSTANCE: NoteDatabase? = null

        fun getAppDataBase(context: Context): NoteDatabase? {
            if (INSTANCE == null){
                synchronized(NoteDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext, NoteDatabase::class.java, "note_database")
                            .addCallback(roomCallBack)
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            synchronized(NoteDatabase::class) {
                INSTANCE = null
            }
        }

        val roomCallBack = object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                Observable.fromCallable {
                    with(INSTANCE?.noteDao()){
                        this?.insert(Note(title="Title 1", description = "Description 1", priority = 1))
                        this?.insert(Note(title="Title 2", description = "Description 2", priority = 2))
                        this?.insert(Note(title="Title 3", description = "Description 3", priority = 3))
                    }
                }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
            }
        }
    }
}