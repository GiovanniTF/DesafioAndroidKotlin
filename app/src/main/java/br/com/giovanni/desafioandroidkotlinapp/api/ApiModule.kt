package br.com.giovanni.desafioandroidkotlinapp.api

import br.com.giovanni.desafioandroidkotlinapp.api.WebClient.createEndpoint
import org.koin.dsl.module

val apiModule = module {
    single { createEndpoint() }
}