package ru.netology.nmedia.viewModel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.PostRepository
import ru.netology.nmedia.dto.PostRepositoryImpl

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryImpl()

    val data = repository.get()

    fun likeById(post: Post) = repository.likeById(post.id)
    fun repostById(post: Post) = repository.repostById(post.id)
}