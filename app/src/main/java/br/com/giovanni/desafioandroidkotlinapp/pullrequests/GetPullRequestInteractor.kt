package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import br.com.giovanni.desafioandroidkotlinapp.api.Endpoint
import br.com.giovanni.desafioandroidkotlinapp.api.PullRequestPosts
import retrofit2.Response

class GetPullRequestInteractor(private val endpoint: Endpoint){
    suspend fun execute(userArgs: String, repositoryArgs: String): Response<List<PullRequestPosts>> = endpoint.getRequest(userArgs, repositoryArgs)
}