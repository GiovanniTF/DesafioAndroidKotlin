package br.com.giovanni.desafioandroidkotlinapp.models

import retrofit2.Response
import retrofit2.http.GET

interface Endpoint {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    suspend fun getPosts() : Response<ApiResponse<Posts>>
}