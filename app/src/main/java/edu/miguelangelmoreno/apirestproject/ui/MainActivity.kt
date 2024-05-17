package edu.miguelangelmoreno.apirestproject.ui

import android.app.AlertDialog
import android.app.Dialog
import android.app.ZygotePreload
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import edu.miguelangelmoreno.apirestproject.R
import edu.miguelangelmoreno.apirestproject.RoomApplication
import edu.miguelangelmoreno.apirestproject.data.WordState
import edu.miguelangelmoreno.apirestproject.data.WordsDataSource
import edu.miguelangelmoreno.apirestproject.data.WordsRepository
import edu.miguelangelmoreno.apirestproject.databinding.ActivityMainBinding
import edu.miguelangelmoreno.apirestproject.model.Word
import edu.miguelangelmoreno.apirestproject.utils.checkConnection
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WordsAdapter

    private val vm: MainViewModel by viewModels {
        val db = (application as RoomApplication).wordsStateDB
        val dataSource = WordsDataSource(db.stateWordsDao())
        val repository = WordsRepository(dataSource)
        MainViewModelFactory(repository)
    }


    private var onFavScreen = false
    private var sortedAlphabetically = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getWords()
        applyFilter(adapter.currentList)
    }

    override fun onStart() {
        super.onStart()

        binding.swipeRefresh.setOnRefreshListener { getWords() }
        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favoriteWords -> {
                    onFavScreen = true
                    var favoriteWords = adapter.currentList.filter {
                        it.favorito
                    }
                    if (favoriteWords.isEmpty()) {
                        binding.tvNoFavs.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    } else {
                        binding.tvNoFavs.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                    applyFilter(favoriteWords)
                    true
                }

                R.id.allWords -> {
                    onFavScreen = false
                    getWords()
                    applyFilter(adapter.currentList)
                    true
                }

                else -> false
            }
        }
        binding.materialToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.opt_randomWord -> {
                    val randomWord = adapter.currentList.random()
                    AlertDialog.Builder(this@MainActivity).apply {
                        setTitle(randomWord.palabra)
                        setMessage(randomWord.definicion)
                        setPositiveButton(
                            android.R.string.ok,
                            null
                        ).show()
                    }
                    true
                }

                R.id.opt_sortAlphabetically -> {
                    sortedAlphabetically = !sortedAlphabetically
                    applyFilter(adapter.currentList)
                    true
                }

                else -> false
            }
        }
    }

    private fun initRecyclerView() {
        adapter = WordsAdapter(
            onClick = {
                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle(it.palabra)
                    setMessage(it.definicion)
                    setPositiveButton(
                        android.R.string.ok,
                        null
                    ).show()
                }
            },
            onClickFav = {
                it.favorito = !it.favorito
                vm.saveState(
                    WordState(
                        it.idPalabra,
                        it.favorito
                    )
                )
            }
        )
        binding.recyclerView.adapter = adapter
    }

    private fun getWords() {
        adapter.submitList(emptyList())
        if (checkConnection(this)) {
            lifecycleScope.launch {
                binding.swipeRefresh.isRefreshing = true
                combine(vm.currentWords, vm.currentWordStates) { words, wordStates ->
                    words.forEach { word ->
                        val wordState = wordStates.find { it.idWord == word.idPalabra }
                        if (wordState != null) {
                            word.favorito = wordState.stateFavorite
                        }
                    }

                    if (onFavScreen) {
                        var favoriteWords = words.filter {
                            it.favorito
                        }
                        if (favoriteWords.isEmpty()) {
                            binding.tvNoFavs.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        } else {
                            binding.tvNoFavs.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        adapter.submitList(favoriteWords)
                    } else {
                        binding.tvNoFavs.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.submitList(words)
                    }

                    binding.swipeRefresh.isRefreshing = false
                }.catch {
                    Toast.makeText(
                        this@MainActivity, it.message, Toast.LENGTH_SHORT
                    ).show()
                }.collect {}
            }
        } else {
            binding.swipeRefresh.isRefreshing = false
            Toast.makeText(
                this@MainActivity,
                getString(R.string.txt_noConnection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun applyFilter(list: List<Word>) {
        if (sortedAlphabetically) {
            adapter.submitList(list.sortedByDescending { it.palabra })
        } else {
            adapter.submitList(list.sortedBy { it.palabra })
        }
    }
}