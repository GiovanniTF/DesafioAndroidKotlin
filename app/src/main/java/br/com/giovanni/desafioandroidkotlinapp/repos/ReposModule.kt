package br.com.giovanni.desafioandroidkotlinapp.repos

import br.com.giovanni.desafioandroidkotlinapp.pullrequests.GetPullRequestInteractor
import br.com.giovanni.desafioandroidkotlinapp.pullrequests.PullRequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val itemModule = module {
    factory { GetReposInteractor(get()) }
    viewModel { ReposViewModel(get()) }
    factory { GetPullRequestInteractor(get()) }
    viewModel { PullRequestViewModel(get()) }
}