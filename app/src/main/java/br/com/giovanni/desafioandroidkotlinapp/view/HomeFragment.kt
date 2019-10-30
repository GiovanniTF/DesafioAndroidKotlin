package br.com.giovanni.desafioandroidkotlinapp.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.giovanni.desafioandroidkotlinapp.R
import br.com.giovanni.desafioandroidkotlinapp.models.*
import br.com.giovanni.desafioandroidkotlinapp.viewModel.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter {
            //Passar post

        }

        recyclerViewId.adapter = adapter
        recyclerViewId.layoutManager = LinearLayoutManager(context)

        getData()
    }


    private fun getData() {
        val retrofitClient = WebClient
            .getRetrofitInstance("https://api.github.com/")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<ApiResponse<Posts>> {
            override fun onFailure(call: Call<ApiResponse<Posts>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                Log.e("MyActivity", "Mensagem de erro da requisição " + t.message)
            }

            override fun onResponse(
                call: Call<ApiResponse<Posts>>,
                response: Response<ApiResponse<Posts>>
            ) {
                adapter.submitList(response.body()?.response)
            }

        })
    }


}
