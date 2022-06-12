package ru.netology.nmedia.viewModel

import SingleLiveEvent
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.adapter.PostInteractionListener
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.*
import ru.netology.nmedia.data.impl.PostRepositoryImpl
import ru.netology.nmedia.db.AppDb

class PostViewModel(
    application: Application
): AndroidViewModel(application),
    PostInteractionListener {
    private val repository: PostRepository =
        PostRepositoryImpl(
            dao = AppDb.getInstance(
                context = application
            ).postDao
        )

    val data by repository::data

    val repostPostContent = SingleLiveEvent<String>()
    val navigateToPostContentScreenEvent = SingleLiveEvent<String>()
    val navigateToPostScreenEvent = SingleLiveEvent<Post>()
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
        navigateToPostContentScreenEvent.value = post.content

    }

    override fun onVideoPlayButtonClicked(post: Post) {
        videoUrl.value = post.videoUrl
    }

    override fun onVideoBannerClicked(post: Post) {
        videoUrl.value = post.videoUrl
    }

    override fun onPostClicked(post: Post) {
        navigateToPostScreenEvent.value = post
    }


}