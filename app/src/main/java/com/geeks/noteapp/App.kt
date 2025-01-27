package com.geeks.noteapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.geeks.noteapp.data.db.AppDatabase
import com.geeks.noteapp.utils.PreferenceHelper

class App : Application() {
    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(context = this)
        getInstance()
    }

    private fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let { context: Context ->
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    name = "note.database"
                ).fallbackToDestructiveMigrationFrom().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}