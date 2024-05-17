package edu.miguelangelmoreno.apirestproject.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordsDataSource (private val db: StateWordsDao) {
    private val api = Retrofit2Api.getRetrofit2Api()
    fun getWords() = flow {
        emit(api.getWords())
    }

    fun getAllWordState() : Flow<List<WordState>> {
        return db.getAllStateWords()
    }

    suspend fun insertStateWord(wordState: WordState){
        db.insertStateWord(wordState)
    }
}