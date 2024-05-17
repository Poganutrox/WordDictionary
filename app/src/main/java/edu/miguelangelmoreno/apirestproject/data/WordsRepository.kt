package edu.miguelangelmoreno.apirestproject.data

import edu.miguelangelmoreno.apirestproject.model.Word
import kotlinx.coroutines.flow.Flow

class WordsRepository (private val dataSource: WordsDataSource) {
    fun fetchSWords(): Flow<List<Word>> {
        return dataSource.getWords()
    }

    fun fetchAllWordState() : Flow<List<WordState>>{
        return dataSource.getAllWordState()
    }

    suspend fun insertStateWord(wordState: WordState){
        dataSource.insertStateWord(wordState)
    }
}
