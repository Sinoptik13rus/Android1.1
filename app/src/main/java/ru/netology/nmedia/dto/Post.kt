package ru.netology.nmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val counterLike: Int = 0,
    val counterRepost: Int = 0,
    val counterView: Int = 0,
    val likedByMe: Boolean = false
)