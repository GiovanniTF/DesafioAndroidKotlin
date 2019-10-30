package br.com.giovanni.desafioandroidkotlinapp.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.giovanni.desafioandroidkotlinapp.R
import br.com.giovanni.desafioandroidkotlinapp.models.Posts
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_list_adapter.view.*

class HomeAdapter (private val clickListener: (Posts) -> Unit): ListAdapter<Posts, HomeAdapter.ViewHolder>(
    PostsDiffCallback()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.fragment_list_adapter,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(posts: Posts, clickListener: (Posts) -> Unit) {

            Glide.with(itemView.context)
                .load(posts.owner.avatar_url)
                .into(itemView.imgUserId)
            itemView.txtNameRepositoryId.text = posts.name
            itemView.txtUsernameId.text = posts.owner.login
            itemView.txtFullUsernameId.text = posts.full_name
            itemView.txtDescriptionRespositoryId.text = posts.description
            itemView.txtForksId.text = posts.forks.toString()
            itemView.txtStarsId.text = posts.stargazers_count.toString()

            itemView.setOnClickListener{clickListener(posts)}
        }
    }

    class PostsDiffCallback : DiffUtil.ItemCallback<Posts>() {

        override fun areItemsTheSame(oldItem: Posts, newItem: Posts):
                Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Posts, newItem: Posts):
                Boolean = oldItem == newItem

    }

}