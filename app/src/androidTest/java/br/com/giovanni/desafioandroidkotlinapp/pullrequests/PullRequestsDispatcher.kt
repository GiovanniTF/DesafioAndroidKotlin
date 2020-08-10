package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import android.content.res.AssetManager
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import okhttp3.mockwebserver.SocketPolicy

class PullRequestsDispatcher (
    private val isError: Boolean = false,
    private val isTimeOut: Boolean = false
//    val asset: String
) : Dispatcher(){
    override fun dispatch(request: RecordedRequest): MockResponse {

        return if (request.path == "https://api.github.com/repos/CyC2018/CS-Notes/pulls") {
            when{
                isError -> {
                    MockResponse().setResponseCode(404).setBody(PULL)
                }
                isTimeOut -> {
                    MockResponse().apply {
                        socketPolicy = SocketPolicy.NO_RESPONSE
                    }
                }
                else-> {
                    MockResponse().setResponseCode(200).setBody(PULL)
                }
            }
        } else {
            MockResponse().setResponseCode(404)
        }
    }
}