package edu.miguelangelmoreno.apirestproject.model


import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("definicion")
    val definicion: String,
    @SerializedName("idPalabra")
    val idPalabra: Int,
    @SerializedName("palabra")
    val palabra: String,

    var favorito: Boolean = false
)