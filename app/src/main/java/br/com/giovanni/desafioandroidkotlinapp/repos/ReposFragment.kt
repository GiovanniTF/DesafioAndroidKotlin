package br.com.giovanni.desafioandroidkotlinapp.repos

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.giovanni.desafioandroidkotlinapp.EndlessRecyclerViewScrollListener
import br.com.giovanni.desafioandroidkotlinapp.R
import kotlinx.android.synthetic.main.fragment_repos.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposFragment : Fragment(R.layout.fragment_repos) {
    private lateinit var adapter: ReposAdapter
    private val reposViewModel: ReposViewModel by viewModel()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ReposAdapter {
            val loginArgs = it.owner.login
            val name = it.name
            val action = ReposFragmentDirections.actionReposToList(loginArgs, name)
            view.findNavController().navigate(action)
        }

        val layoutManager = LinearLayoutManager(requireContext())

        recyclerViewId.adapter = adapter
        recyclerViewId.layoutManager = layoutManager

        reposViewModel.getPostViewState().observe(viewLifecycleOwner, Observer<ReposViewState> {
            when (it) {
                is ReposViewState.Error -> alertDialog(getString(R.string.error_api_response))
                is ReposViewState.ErrorTimeOut -> alertDialog(getString(R.string.error_conection))
                is ReposViewState.Repos -> {
                    progressBarId.visibility = View.GONE
                    adapter.submitList(it.posts)
                }
            }
        })

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                reposViewModel.getPosts()
                progressBarId.visibility = View.VISIBLE
            }
        }

        recyclerViewId.addOnScrollListener(scrollListener)

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