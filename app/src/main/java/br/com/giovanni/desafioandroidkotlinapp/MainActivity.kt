package br.com.giovanni.desafioandroidkotlinapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.giovanni.desafioandroidkotlinapp.models.ApiResponse
import br.com.giovanni.desafioandroidkotlinapp.models.Endpoint
import br.com.giovanni.desafioandroidkotlinapp.models.Posts
import br.com.giovanni.desafioandroidkotlinapp.models.WebClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    fun getData() {
        val retrofitClient = WebClient
            .getRetrofitInstance("https://api.github.com/")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<ApiResponse<Posts>> {
            override fun onFailure(call: Call<ApiResponse<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                Log.e("MyActivity", "Mensagem de erro da requisição " + t.message)
            }

            override fun onResponse(call: Call<ApiResponse<Posts>>, response: Response<ApiResponse<Posts>>) {
                response.body()?.response?.forEach {
                    txtTesteAPI.text = txtTesteAPI.text.toString().plus(it.name).plus("\n")
                }
            }

        })
    }
}
