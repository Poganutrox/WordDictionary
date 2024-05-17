package edu.miguelangelmoreno.apirestproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.miguelangelmoreno.apirestproject.data.WordState
import edu.miguelangelmoreno.apirestproject.data.WordsRepository
import edu.miguelangelmoreno.apirestproject.model.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WordsRepository) : ViewModel(){
    private var _currentWords = repository.fetchSWords()
    val currentWords: Flow<List<Word>>
        get() = _currentWords

    private var _currentWordState = repository.fetchAllWordState()
    val currentWordStates: Flow<List<WordState>>
        get() = _currentWordState

    fun saveState(wordState: WordState){
        viewModelScope.launch {
            repository.insertStateWord(wordState)
        }
    }
}
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: WordsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}