package br.com.giovanni.desafioandroidkotlinapp.pullrequests


import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.giovanni.desafioandroidkotlinapp.R
import kotlinx.android.synthetic.main.fragment_pull_request.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PullRequestFragment : Fragment(R.layout.fragment_pull_request) {

    private var adapter = PullRequestAdapter()

    private val pullRequestViewModel: PullRequestViewModel by viewModel()

//    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        adapter = PullRequestAdapter {
//        // sem ação para clique
//        }

        val layoutManager = LinearLayoutManager(requireContext())

        arguments?.let {
            val safeArgs = PullRequestFragmentArgs.fromBundle(it)
            pullRequestViewModel.getPullRequest(safeArgs.userArgs, safeArgs.repositoryArgs)
        }

        recyclerViewDetailId.adapter = adapter
        recyclerViewDetailId.layoutManager = layoutManager

        pullRequestViewModel.getPullRequestViewState().observe(viewLifecycleOwner, Observer {
            when (it) {
                is PullRequestViewState.Error -> alertDialog(getString(R.string.error_api_response))
                is PullRequestViewState.ErrorTimeOut -> alertDialog(getString(R.string.error_conection))
                is PullRequestViewState.Request -> {
                    adapter.submitList(it.pullRequestPosts)
                }
            }
        })

//        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager){
//            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
//                pullRequestViewModel.getPullRequest()
//            }
//        }
//
//        recyclerViewDetailId.addOnScrollListener(scrollListener)

    }

    private fun alertDialog(messageAlert: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.title_error)
            .setCancelable(false)
            .setMessage(messageAlert)
            .setNegativeButton(R.string.title_button_try_again) { _, _ ->

//                pullRequestViewModel.getPullRequest()
            }
            .show()
    }


}
