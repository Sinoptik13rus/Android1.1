package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    val data: LiveData<List<Post>>

    fun likeById(id: Long)

    fun repostById(id: Long)

    fun removeById(id: Long)

    fun save(post: Post)

    fun update(post: Post)

    companion object {
        const val NEW_POST_ID = 0L
    }
}