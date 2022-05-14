package ru.netology.nmedia.dto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostBinding
import kotlin.properties.Delegates
import kotlin.reflect.KFunction1

typealias onAnyListener = (Post) -> Unit

class PostsAdapter(
    private val onLikeListener: onAnyListener,
    private val onRepostListener: onAnyListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding, onLikeListener, onRepostListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: PostBinding,
    private val onLikeListener: onAnyListener,
    private val onRepostListener: onAnyListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            authorName.text = post.author
            textPost.text = post.content
            date.text = post.published

            likeText.text = counterView(post.counterLike)
            repostText.text = counterView(post.counterRepost)
            viewText.text = counterView(post.counterView)

            likeButton.setImageResource(getLikeIconRes(post.likedByMe))

            likeButton.setOnClickListener {
                onLikeListener(post)
            }

            repostButton.setOnClickListener {
                onRepostListener(post)
            }
        }
    }
}


@DrawableRes
private fun getLikeIconRes(liked: Boolean) =
    if (liked) R.drawable.ic_red_favorite_24dp else R.drawable.ic_favorite_24dp

class PostDiffCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}