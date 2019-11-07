package br.com.giovanni.desafioandroidkotlinapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.giovanni.desafioandroidkotlinapp.R
import br.com.giovanni.desafioandroidkotlinapp.viewModel.ItemViewModel
import br.com.giovanni.desafioandroidkotlinapp.viewModel.ItemViewState
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: HomeAdapter

    private val itemViewModel: ItemViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter {
            //Passar post

        }

        recyclerViewId.adapter = adapter
        recyclerViewId.layoutManager = LinearLayoutManager(requireContext())

        itemViewModel.getItemViewState().observe(this, Observer<ItemViewState> {
            when (it) {
                is ItemViewState.Error -> alertDialog(getString(R.string.error_api_response))
                is ItemViewState.ErrorTimeOut -> alertDialog(getString(R.string.error_conection))
                is ItemViewState.Items -> {
                    progressBarId.visibility = View.GONE
                    adapter.submitList(it.posts)
                }
            }
        })
    }

    private fun alertDialog(messageAlert : String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.title_error)
            .setCancelable(false)
            .setMessage(messageAlert)
            .setNegativeButton(R.string.title_button_try_again) { _, _ ->
                itemViewModel.getPosts()
            }
            .show()
    }
}
