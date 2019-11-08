package br.com.giovanni.desafioandroidkotlinapp.repos

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.giovanni.desafioandroidkotlinapp.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: ReposAdapter

    private val reposViewModel: ReposViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ReposAdapter {
            //Passar post

        }

        recyclerViewId.adapter = adapter
        recyclerViewId.layoutManager = LinearLayoutManager(requireContext())

        reposViewModel.getItemViewState().observe(this, Observer<ReposViewState> {
            when (it) {
                is ReposViewState.Error -> alertDialog(getString(R.string.error_api_response))
                is ReposViewState.ErrorTimeOut -> alertDialog(getString(R.string.error_conection))
                is ReposViewState.Repos -> {
                    progressBarId.visibility = View.GONE
                    adapter.submitList(it.posts)
                }
            }
        })
    }

    private fun alertDialog(messageAlert: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.title_error)
            .setCancelable(false)
            .setMessage(messageAlert)
            .setNegativeButton(R.string.title_button_try_again) { _, _ ->
                reposViewModel.getPosts()
            }
            .show()
    }
}
