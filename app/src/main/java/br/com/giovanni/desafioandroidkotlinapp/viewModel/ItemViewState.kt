package br.com.giovanni.desafioandroidkotlinapp.viewModel

import br.com.giovanni.desafioandroidkotlinapp.models.Posts

sealed class ItemViewState {

    data class Items (val posts: List<Posts>) : ItemViewState()

    object Empty : ItemViewState()

    object Error : ItemViewState()

    object ErrorTimeOut : ItemViewState()

}