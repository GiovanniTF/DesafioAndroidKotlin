package br.com.giovanni.desafioandroidkotlinapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.giovanni.desafioandroidkotlinapp.models.ApiResponse
import br.com.giovanni.desafioandroidkotlinapp.models.Endpoint
import br.com.giovanni.desafioandroidkotlinapp.models.Posts
import br.com.giovanni.desafioandroidkotlinapp.models.WebClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemViewModel : ViewModel() {

    init {
        getPost()
    }

    private val state = MutableLiveData<ItemViewState>()

    val viewState: LiveData<ItemViewState> = state

    fun getItemViewState(): LiveData<ItemViewState> = state

    fun getPost() {

        val retrofitClient = WebClient
            .getRetrofitInstance("https://api.github.com/")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<ApiResponse<Posts>> {

            override fun onFailure(call: Call<ApiResponse<Posts>>, t: Throwable) {
                Log.e("MyActivity", "Erro de requisição " + t.message)

                state.value = ItemViewState.Error
            }

            override fun onResponse(
                call: Call<ApiResponse<Posts>>,
                response: Response<ApiResponse<Posts>>
            ) {
                response.body()?.response?.let {
                    state.value = ItemViewState.Items(it)
                }

            }

        })
    }

}