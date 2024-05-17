package edu.miguelangelmoreno.apirestproject.data

import edu.miguelangelmoreno.apirestproject.model.Word
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class Retrofit2Api {
    companion object {
        private const val BASE_URL = "https://www.javiercarrasco.es/api/words/"
        fun getRetrofit2Api(): Retrofit2ApiInterface {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(Retrofit2ApiInterface::class.java)
        }
    }
}
interface Retrofit2ApiInterface {
    @GET("read")
    suspend fun getWords(): List<Word>
}