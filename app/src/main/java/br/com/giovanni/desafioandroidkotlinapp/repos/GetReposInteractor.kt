package br.com.giovanni.desafioandroidkotlinapp.repos

import br.com.giovanni.desafioandroidkotlinapp.api.ApiResponse
import br.com.giovanni.desafioandroidkotlinapp.api.Endpoint
import br.com.giovanni.desafioandroidkotlinapp.api.Posts
import br.com.giovanni.desafioandroidkotlinapp.api.WebClient
import retrofit2.Response

class GetReposInteractor {

    suspend fun execute(): Response<ApiResponse<Posts>> {
        val retrofitClient =
            WebClient.getRetrofitInstance("https://api.github.com/")

        val endpoint = retrofitClient.create(Endpoint::class.java)

        return endpoint.getPosts()
    }
}