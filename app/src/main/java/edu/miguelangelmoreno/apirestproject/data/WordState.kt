package edu.miguelangelmoreno.apirestproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordState (
    @PrimaryKey
    val idWord: Int = 0,
    val stateFavorite: Boolean = false
)