package com.ressphere.architectureexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="note_table")
data class Note(@PrimaryKey(autoGenerate = true) var id:Int? = null,
                val title:String,
                val description:String,
                @ColumnInfo(name="priority_column") val priority:Int)
