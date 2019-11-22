package br.com.giovanni.desafioandroidkotlinapp

import br.com.giovanni.desafioandroidkotlinapp.api.WebClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher() {
    val mockWebServer by lazy { MockWebServer() }

    override fun starting(description: Description?) {
        super.starting(description)
        mockWebServer.start(8080)
        WebClient.baseUrl = mockWebServer.url("").toString()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        mockWebServer.shutdown()
    }
}