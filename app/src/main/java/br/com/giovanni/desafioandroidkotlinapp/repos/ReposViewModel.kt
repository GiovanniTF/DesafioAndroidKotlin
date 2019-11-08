package br.com.giovanni.desafioandroidkotlinapp.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

class ReposViewModel(private val getReposInteractor: GetReposInteractor) : ViewModel() {

    private val state = MutableLiveData<ReposViewState>()

    init {
        getPosts()
    }

    fun getItemViewState(): LiveData<ReposViewState> = state

    fun getPosts() {
        viewModelScope.launch {

            try {
                val apiResponse = getReposInteractor.execute()

                if (apiResponse.isSuccessful) {
                    val posts = apiResponse.body()?.response
                    posts?.let {
                        if (it.isEmpty()) {
                            state.value =
                                ReposViewState.Empty
                        } else {
                            state.value =
                                ReposViewState.Repos(
                                    it
                                )
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