package br.com.giovanni.desafioandroidkotlinapp.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getPosts(@Query("page") page: Int):
            Response<ApiResponse<Posts>>

    @GET("repos/{userArgs}/{repositoryArgs}/pulls")
    suspend fun getRequest(@Path("userArgs") userArgs: String,
                           @Path("repositoryArgs") repositoryArgs: String):
            Response<List<PullRequestPosts>>
}