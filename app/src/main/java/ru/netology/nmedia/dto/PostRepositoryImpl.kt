package ru.netology.nmedia.dto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryImpl : PostRepository {

    private var post = Post(
        id = 0L,
        author = "Адриано Челентано",
        content = "Привет! Это новый Адриано Челентано!",
        published = "05.05.2022"
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {

        post = if (!post.likedByMe) {
            post.copy(counterLike = post.counterLike + 1, likedByMe = !post.likedByMe)
        } else {
            post.copy(counterLike = post.counterLike - 1, likedByMe = !post.likedByMe)
        }

        data.value = post
    }

    override fun repost() {
        post = post.copy(counterRepost = post.counterRepost + 1)
        data.value = post
    }

}