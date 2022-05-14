package ru.netology.nmedia.dto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryImpl : PostRepository {

    private var posts = listOf(
        Post(
            id = 0L,
            author = "Адриано Челентано",
            content = "Привет! Это новый Адриано Челентано!",
            published = "05.05.2022"
        ),
        Post(
            id = 1L,
            author = "Массимо Каррера",
            content = "Привет! Это новый тренер по футболу",
            published = "14.05.2022"
        ),
        Post(
            id = 2L,
            author = "Олег Газманов",
            content = "Привет! Это новый вокал от звезды",
            published = "12.05.2022"
        ),
        Post(
            id = 3L,
            author = "Дэвид Духовны",
            content = "Привет! Это новый сериал",
            published = "10.05.2019"
        ),
        Post(
            id = 4L,
            author = "Антонио Конте",
            content = "Привет! Это новый контракт с тренером",
            published = "09.02.2021"
        ),
        Post(
            id = 6L,
            author = "Инспектор Гаджет",
            content = "Привет! Это новый Гаджет",
            published = "21.05.2009"
        )
    )

    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {

        posts = posts.map {
            if (it.id != id) it else {
                if (!it.likedByMe) {
                    it.copy(counterLike = it.counterLike + 1, likedByMe = !it.likedByMe)
                } else {
                    it.copy(counterLike = it.counterLike - 1, likedByMe = !it.likedByMe)
                }
            }
        }
        data.value = posts

    }

    override fun repostById(id: Long) {

        posts = posts.map {
            if (it.id != id) it else it.copy(counterRepost = it.counterRepost + 1)
        }
        data.value = posts

    }
}
