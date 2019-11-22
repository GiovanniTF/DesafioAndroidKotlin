package br.com.giovanni.desafioandroidkotlinapp.repos

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import okhttp3.mockwebserver.SocketPolicy

class ReposDispatcher(
    private val isError: Boolean = false,
    private val isTimeOut: Boolean = false
) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return if (request.path == "/search/repositories?q=language:Java&sort=stars&page=1") {
            when {
                isError -> {
                    MockResponse().setResponseCode(404).setBody(REPOS)
                }
                isTimeOut -> {
                    MockResponse().apply {
                        socketPolicy = SocketPolicy.NO_RESPONSE
                    }
                }
                else -> {
                    MockResponse().setResponseCode(200).setBody(REPOS)
                }
            }
        } else {
            MockResponse().setResponseCode(404)
        }
    }
}