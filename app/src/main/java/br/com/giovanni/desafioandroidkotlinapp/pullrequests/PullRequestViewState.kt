package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import br.com.giovanni.desafioandroidkotlinapp.api.PullRequestPosts

sealed class PullRequestViewState {

    data class Request (val pullRequestPosts: List<PullRequestPosts>) : PullRequestViewState()

    object Empty : PullRequestViewState()

    object Error : PullRequestViewState()

    object ErrorTimeOut : PullRequestViewState()

}