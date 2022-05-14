package ru.netology.nmedia.viewModel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.PostRepository
import ru.netology.nmedia.dto.PostRepositoryImpl

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryImpl()

    val data by repository::getAll

    fun onLikeClickedId(id: Long) = repository.likeById(id)
    fun onRepostClickedId(id: Long) = repository.repostById(id)
}