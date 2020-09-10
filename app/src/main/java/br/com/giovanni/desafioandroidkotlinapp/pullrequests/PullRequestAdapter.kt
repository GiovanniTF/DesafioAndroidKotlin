package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.giovanni.desafioandroidkotlinapp.R
import br.com.giovanni.desafioandroidkotlinapp.api.PullRequestPosts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_pull_request_adapter.view.*

class PullRequestAdapter:
    ListAdapter<PullRequestPosts, PullRequestAdapter.ViewHolder>(
        PostDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.fragment_pull_request_adapter,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pullRequestPosts: PullRequestPosts) {

            Glide.with(itemView.context)
                .load(pullRequestPosts.user.avatar_url)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.imgUserPullRequestId)

            itemView.txtTitlePullRequestId.text = pullRequestPosts.title
            itemView.txtUsernamePullRequestId.text = pullRequestPosts.user.login
            itemView.txtFullUsernamePullRequestId.text = pullRequestPosts.head.repo?.full_name
            itemView.txtDescriptionPullRequestId.text = pullRequestPosts.head.repo?.description
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<PullRequestPosts>() {

        override fun areItemsTheSame(oldItem: PullRequestPosts, newItem: PullRequestPosts):
                Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PullRequestPosts, newItem: PullRequestPosts):
                Boolean = oldItem == newItem
    }

}