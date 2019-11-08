package br.com.giovanni.desafioandroidkotlinapp

import android.app.Application
import br.com.giovanni.desafioandroidkotlinapp.repos.itemModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DesafioAndroidKotlinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DesafioAndroidKotlinApp)
            modules(listOf(itemModule))
        }
    }
}
