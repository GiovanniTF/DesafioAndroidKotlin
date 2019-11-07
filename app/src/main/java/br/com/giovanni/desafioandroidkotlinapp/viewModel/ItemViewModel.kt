package br.com.giovanni.desafioandroidkotlinapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.giovanni.desafioandroidkotlinapp.models.Interactor
import kotlinx.coroutines.launch
import java.io.IOException

class ItemViewModel(private val interactor: Interactor) : ViewModel() {

    private val state = MutableLiveData<ItemViewState>()

    init {
        getPosts()
    }

    fun getItemViewState(): LiveData<ItemViewState> = state

    fun getPosts() {
        viewModelScope.launch {

            try {
                val apiResponse = interactor.getData()

                if (apiResponse.isSuccessful) {
                    val posts = apiResponse.body()?.response
                    posts?.let {
                        if (it.isEmpty()) {
                            state.value = ItemViewState.Empty
                        } else {
                            state.value = ItemViewState.Items(it)
                        }
                    }

                } else {
                    state.value = ItemViewState.Error
                }
            } catch (exception: IOException) {
                state.value = ItemViewState.ErrorTimeOut
            }
        }
    }
}