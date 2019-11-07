package br.com.giovanni.desafioandroidkotlinapp

import android.app.Application
import br.com.giovanni.desafioandroidkotlinapp.modules.itemModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CustomApplication)
            modules(listOf(itemModule))
        }
    }
}
