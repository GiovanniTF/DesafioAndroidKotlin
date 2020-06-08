package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.giovanni.desafioandroidkotlinapp.api.PullRequestPosts
import kotlinx.coroutines.launch
import java.io.IOException

class PullRequestViewModel(private val getPullRequestInteractor: GetPullRequestInteractor) :
    ViewModel() {

    private val state = MutableLiveData<PullRequestViewState>()
    private var listPullRequest = listOf<PullRequestPosts>()


//    init {
//        getPullRequest()
//    }

    fun getPullRequestViewState(): LiveData<PullRequestViewState> = state

    fun getPullRequest(userArgs: String, repositoryArgs: String) {
        viewModelScope.launch {
            try {
                val apiResponse = getPullRequestInteractor.execute(userArgs, repositoryArgs)

                if (apiResponse.isSuccessful) {
                    val pullRequest = apiResponse.body()
                    pullRequest?.let {
                        if (it.isEmpty()) {
                            state.value =
                                PullRequestViewState.Empty
                        } else {
                            listPullRequest = listPullRequest + it
                            state.value =
                                PullRequestViewState.Request(listPullRequest)
                        }
                    }
                } else {
                    state.value =
                        PullRequestViewState.Error
                }
            } catch (exception: IOException) {
                state.value =
                    PullRequestViewState.ErrorTimeOut
            }
        }
    }
}