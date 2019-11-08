package br.com.giovanni.desafioandroidkotlinapp.repos

import br.com.giovanni.desafioandroidkotlinapp.api.Posts

sealed class ReposViewState {

    data class Repos (val posts: List<Posts>) : ReposViewState()

    object Empty : ReposViewState()

    object Error : ReposViewState()

    object ErrorTimeOut : ReposViewState()

}