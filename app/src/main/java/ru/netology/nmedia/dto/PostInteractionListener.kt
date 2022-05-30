package ru.netology.nmedia.dto

interface PostInteractionListener {

    fun onLikeListener(post: Post)

    fun onRepostListener(post: Post)

    fun onRemoveListener(post: Post)

    fun onEditListener(post: Post)

    fun onVideoPlayButtonClicked(post: Post)

    fun onVideoBannerClicked(post: Post)

}