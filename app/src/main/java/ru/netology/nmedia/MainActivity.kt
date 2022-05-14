package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.PostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.counterView
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        viewModel.data.observe(this) { posts ->
            binding.container.removeAllViews()
            posts.map { post ->
                PostBinding.inflate(layoutInflater, binding.container, true).apply {
                    authorName.text = post.author
                    textPost.text = post.content
                    date.text = post.published

                    likeText.text = counterView(post.counterLike)
                    repostText.text = counterView(post.counterRepost)
                    viewText.text = counterView(post.counterView)

                    likeButton.setImageResource(getLikeIconRes(post.likedByMe))

                    likeButton.setOnClickListener {
                        viewModel.onLikeClickedId(post.id)
                    }

                    repostButton.setOnClickListener {
                        viewModel.onRepostClickedId(post.id)
                    }
                }.root
            }
        }
    }

    @DrawableRes
    private fun getLikeIconRes(liked: Boolean) =
        if (liked) R.drawable.ic_red_favorite_24dp else R.drawable.ic_favorite_24dp
}
