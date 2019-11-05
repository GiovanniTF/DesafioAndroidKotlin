package br.com.giovanni.desafioandroidkotlinapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.giovanni.desafioandroidkotlinapp.R
import br.com.giovanni.desafioandroidkotlinapp.viewModel.ItemViewModel
import br.com.giovanni.desafioandroidkotlinapp.viewModel.ItemViewState
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: HomeAdapter

    private lateinit var viewModel: ItemViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter {
            //Passar post

        }

        recyclerViewId.adapter = adapter
        recyclerViewId.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProviders.of(this)[ItemViewModel::class.java]
        viewModel.getItemViewState().observe(this, Observer<ItemViewState> {
            when (it) {
                is ItemViewState.Error -> alertDialog()
                is ItemViewState.Items -> {
                    progressBarId.visibility = View.GONE
                    adapter.submitList(it.posts)
                }
            }
        })
    }

    private fun alertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Algo deu errado.")
            .setCancelable(false)
            .setMessage("Por algum motivo, não conseguimos carregar as informações necessárias.")
            .setNegativeButton("Tentar novamente") { _, _ ->
                viewModel.getPost()
            }
            .show()
    }
}
