package ru.netology.nmedia.dto

import androidx.lifecycle.LiveData

interface PostRepository {
    val getAll: LiveData<List<Post>>

    fun likeById(id: Long)

    fun repostById(id: Long)
}