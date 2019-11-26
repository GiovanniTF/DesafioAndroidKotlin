package br.com.giovanni.desafioandroidkotlinapp.repos

import br.com.giovanni.desafioandroidkotlinapp.api.ApiResponse
import br.com.giovanni.desafioandroidkotlinapp.api.Endpoint
import br.com.giovanni.desafioandroidkotlinapp.api.Posts
import retrofit2.Response

class GetReposInteractor(private val endpoint: Endpoint) {
    suspend fun execute(page : Int): Response<ApiResponse<Posts>> = endpoint.getPosts(page)
}