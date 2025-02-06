package com.geeks.noteapp.model.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.noteapp.model.data.db.daos.NoteDao
import com.geeks.noteapp.model.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}