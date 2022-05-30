package ru.netology.nmedia.viewModel

import SingleLiveEvent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.PostInteractionListener
import ru.netology.nmedia.dto.PostRepository
import ru.netology.nmedia.dto.PostRepositoryImpl

class PostViewModel: ViewModel(), PostInteractionListener {
    private val repository: PostRepository = PostRepositoryImpl()

    val data = repository.get()

    val repostPostContent = SingleLiveEvent<String>()
    val navigateToPostContentScreenEvent = SingleLiveEvent<Unit>()
    val videoUrl = SingleLiveEvent<String>()
    val currentPost = MutableLiveData<Post?>(null)

    fun onSaveListener(content: String) {
        val post = currentPost.value?.copy(
            content = content
        ) ?: Post(
            id = PostRepository.NEW_POST_ID,
            author = "Масимо Каррера",
            content = content,
            published = "21.05.2022"
        )
        repository.save(post)
        currentPost.value = null
    }

    fun onAddClicked() {
        currentPost.value = null
        navigateToPostContentScreenEvent.call()
    }

    override fun onLikeListener(post: Post) = repository.likeById(post.id)

    override fun onRepostListener(post: Post) {
        repostPostContent.value = post.content
        repository.repostById(post.id)
    }
    override fun onRemoveListener(post: Post) = repository.removeById(post.id)

    override fun onEditListener(post: Post) {
        currentPost.value = post
        navigateToPostContentScreenEvent.call()
    }

    override fun onVideoPlayButtonClicked(post: Post) {
        videoUrl.value = post.videoUrl
    }

    override fun onVideoBannerClicked(post: Post) {
        videoUrl.value = post.videoUrl
    }

}