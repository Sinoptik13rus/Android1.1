package ru.netology.nmedia.adapter

import ru.netology.nmedia.dto.Post

interface PostInteractionListener {

    fun onLikeListener(post: Post)

    fun onRepostListener(post: Post)

    fun onRemoveListener(post: Post)

    fun onEditListener(post: Post)

    fun onVideoPlayButtonClicked(post: Post)

    fun onVideoBannerClicked(post: Post)

    fun onPostClicked(post: Post)

}