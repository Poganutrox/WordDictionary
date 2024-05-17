package edu.miguelangelmoreno.apirestproject.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Database(entities = [WordState::class], version = 1)
abstract class WordsDataBase : RoomDatabase() {
    abstract fun stateWordsDao(): StateWordsDao
}

@Dao
interface StateWordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStateWord(stateWord: WordState)

    @Query("SELECT * FROM WordState")
    fun getAllStateWords(): Flow<List<WordState>>
}