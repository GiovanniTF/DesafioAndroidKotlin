package br.com.giovanni.desafioandroidkotlinapp.modules

import br.com.giovanni.desafioandroidkotlinapp.models.Interactor
import br.com.giovanni.desafioandroidkotlinapp.viewModel.ItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val itemModule = module {
    factory { Interactor() }
    viewModel { ItemViewModel(get()) }
}