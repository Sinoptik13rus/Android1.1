package ru.netology.nmedia.viewModel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.PostRepository
import ru.netology.nmedia.dto.PostRepositoryImpl

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryImpl()

    val data by repository::data

    fun onLikeClicked() = repository.like()
    fun onRepostClicked() = repository.repost()
}