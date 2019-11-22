package br.com.giovanni.desafioandroidkotlinapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {
    var baseUrl = "https://api.github.com/"

    fun createEndpoint(): Endpoint {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)
    }
}

