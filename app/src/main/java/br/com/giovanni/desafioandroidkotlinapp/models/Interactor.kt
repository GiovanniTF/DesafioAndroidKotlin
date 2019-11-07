package br.com.giovanni.desafioandroidkotlinapp.models

import retrofit2.Response

class Interactor {

    suspend fun getData(): Response<ApiResponse<Posts>> {
        val retrofitClient = WebClient
            .getRetrofitInstance("https://api.github.com/")

        val endpoint = retrofitClient.create(Endpoint::class.java)

        return endpoint.getPosts()
    }
}