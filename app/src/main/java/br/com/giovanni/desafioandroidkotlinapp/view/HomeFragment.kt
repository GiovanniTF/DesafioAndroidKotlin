package br.com.giovanni.desafioandroidkotlinapp.view


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import br.com.giovanni.desafioandroidkotlinapp.R
import br.com.giovanni.desafioandroidkotlinapp.models.ApiResponse
import br.com.giovanni.desafioandroidkotlinapp.models.Endpoint
import br.com.giovanni.desafioandroidkotlinapp.models.Posts
import br.com.giovanni.desafioandroidkotlinapp.models.WebClient
import br.com.giovanni.desafioandroidkotlinapp.viewModel.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter {
            //Passar post

        }

        recyclerViewId.adapter = adapter

        getData()
    }


    private fun getData() {
        val retrofitClient = WebClient
            .getRetrofitInstance("https://api.github.com/")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<ApiResponse<Posts>> {

            override fun onFailure(call: Call<ApiResponse<Posts>>, t: Throwable) {
                Log.e("MyActivity", "Erro de requisição " + t.message)

                 AlertDialog.Builder(context)
                     .setTitle("Algo deu errado.")
                     .setCancelable(false)
                     .setMessage("Por algum motivo, não conseguimos carregar as informações necessárias.")
                     .setNegativeButton("Tentar novamente", DialogInterface.OnClickListener{dialog, id ->
                         getData()
                     })
                     .show()
            }

            override fun onResponse(
                call: Call<ApiResponse<Posts>>,
                response: Response<ApiResponse<Posts>>
            ) {
                progressBarId.visibility = View.GONE
                adapter.submitList(response.body()?.response)
            }

        })
    }


}
