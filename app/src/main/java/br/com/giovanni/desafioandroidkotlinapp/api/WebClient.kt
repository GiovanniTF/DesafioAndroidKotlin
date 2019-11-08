package br.com.giovanni.desafioandroidkotlinapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClient{
    fun createEndpoint(): Endpoint {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)
    }
}

