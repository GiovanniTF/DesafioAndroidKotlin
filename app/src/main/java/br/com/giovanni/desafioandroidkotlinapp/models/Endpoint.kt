package br.com.giovanni.desafioandroidkotlinapp.models

import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getPosts() : Call<ApiResponse<Posts>>
}