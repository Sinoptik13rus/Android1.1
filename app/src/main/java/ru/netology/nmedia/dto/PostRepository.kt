package ru.netology.nmedia.dto

import androidx.lifecycle.LiveData

interface PostRepository {
    val data: LiveData<Post>

    fun like()

    fun repost()
}