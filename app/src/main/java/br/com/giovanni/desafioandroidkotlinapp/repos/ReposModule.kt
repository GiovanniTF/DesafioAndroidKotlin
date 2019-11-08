package br.com.giovanni.desafioandroidkotlinapp.repos

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val itemModule = module {
    factory { GetReposInteractor() }
    viewModel { ReposViewModel(get()) }
}