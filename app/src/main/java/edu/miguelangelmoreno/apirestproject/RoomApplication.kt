package edu.miguelangelmoreno.apirestproject

import android.app.Application
import androidx.room.Room
import edu.miguelangelmoreno.apirestproject.data.WordsDataBase

class RoomApplication : Application() {
    lateinit var wordsStateDB: WordsDataBase
        private set

    override fun onCreate() {
        super.onCreate()
        wordsStateDB = Room.databaseBuilder(
            this,
            WordsDataBase::class.java, "StateWords-db"
        ).build()
    }
}