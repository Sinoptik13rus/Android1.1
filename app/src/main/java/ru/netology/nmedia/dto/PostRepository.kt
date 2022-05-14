package ru.netology.nmedia.dto

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<List<Post>>

    fun likeById(id: Long)

    fun repostById(id: Long)
}