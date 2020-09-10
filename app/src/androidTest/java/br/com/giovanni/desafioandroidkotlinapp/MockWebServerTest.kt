package br.com.giovanni.desafioandroidkotlinapp

import androidx.test.espresso.IdlingRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.rules.TestWatcher

class MockWebServerTest : TestWatcher(){

    val mockWebServer by lazy { MockWebServer() }

    @Before
    fun setup(){
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp", OkHttpClient()
            ))
    }

    @After
    fun  teardown () {
        mockWebServer.shutdown ()
    }

}