package edu.miguelangelmoreno.apirestproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.miguelangelmoreno.apirestproject.R
import edu.miguelangelmoreno.apirestproject.databinding.ItemWordBinding
import edu.miguelangelmoreno.apirestproject.model.Word

class WordsAdapter(
    val onClick: (Word) -> Unit,
    val onClickFav: (Word) -> Unit
) : ListAdapter<Word, WordsAdapter.WordsViewHolder>(WordsDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        return WordsViewHolder(
            ItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class WordsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding : ItemWordBinding = ItemWordBinding.bind(view)
        fun bind(word: Word){
            binding.tvWord.text = word.palabra

            binding.cardWord.setOnClickListener{onClick(word)}

            binding.imFavorite.setImageState(
                intArrayOf(R.attr.state_on),
                word.favorito
            )

            binding.imFavorite.setOnClickListener {
                onClickFav(word)
                notifyItemChanged(adapterPosition)
            }

        }
    }
}
class WordsDiffCallback : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.idPalabra == newItem.idPalabra
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }
}