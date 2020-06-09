package br.com.giovanni.desafioandroidkotlinapp.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.giovanni.desafioandroidkotlinapp.api.Posts
import kotlinx.coroutines.launch
import java.io.IOException

class ReposViewModel(private val getReposInteractor: GetReposInteractor) : ViewModel() {

    private val state = MutableLiveData<ReposViewState>()
    private var listPost = listOf<Posts>()

    var page: Int = 1

    init {
        getPosts()
    }

    fun getPostViewState(): LiveData<ReposViewState> = state

    fun getPosts() {
        viewModelScope.launch {
            try {
                val apiResponse = getReposInteractor.execute(page)

                if (apiResponse.isSuccessful) {
                    val posts = apiResponse.body()?.response
                    posts?.let {
                        if (it.isEmpty()) {
                            state.value =
                                ReposViewState.Empty
                        } else {
                            listPost = listPost + it
                            state.value =
                                ReposViewState.Repos(listPost)
                            page++
                        }
                    }

                } else {
                    state.value =
                        ReposViewState.Error
                }
            } catch (exception: IOException) {
                state.value =
                    ReposViewState.ErrorTimeOut
            }
        }
    }
}