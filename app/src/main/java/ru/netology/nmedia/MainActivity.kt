package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.counterView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 0L,
            author = "Адриано Челентано",
            content = "Привет! Это новый Адриано Челентано!",
            published = "05.05.2022",
        )

        with(binding) {
            authorName.text = post.author
            textPost.text = post.content
            date.text = post.published

            likeText.text = counterView(post.counterLike)
            repostText.text = counterView(post.conterRepost)
            viewText.text = counterView(post.counterView)

            if (post.likedByMe) {
                likeButton.setImageResource(R.drawable.ic_red_favorite_24dp)
            }

            likeButton.setOnClickListener {
                post.likedByMe = !post.likedByMe
                binding.likeButton.setImageResource(
                    if (post.likedByMe) R.drawable.ic_red_favorite_24dp else R.drawable.ic_favorite_24dp
                )

                if (post.likedByMe) post.counterLike++ else post.counterLike--
                likeText.text = counterView(post.counterLike)
            }

            repostButton.setOnClickListener {
                post.conterRepost++
                repostText.text = counterView(post.conterRepost)
            }
        }
    }
}
