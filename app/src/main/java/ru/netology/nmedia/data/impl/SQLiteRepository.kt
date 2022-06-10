package ru.netology.nmedia.data.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.db.PostDao
import ru.netology.nmedia.dto.Post

class SQLiteRepository(
    private val dao: PostDao
) : PostRepository {

    private val posts
        get() = checkNotNull(data.value) {
            "Data value should not be null"
        }

    override val data = MutableLiveData(dao.getAll())

    override fun likeById(id: Long) {
        dao.likeById(id)
        data.value = posts.map {
            if (it.id != id) it else {
                if (!it.likedByMe) {
                    it.copy(counterLike = it.counterLike + 1, likedByMe = !it.likedByMe)
                } else {
                    it.copy(counterLike = it.counterLike - 1, likedByMe = !it.likedByMe)
                }
            }
        }
    }

    override fun repostById(id: Long) {
        dao.repostById(id)
        data.value = posts.map {
            if (it.id != id) it else it.copy(counterRepost = it.counterRepost + 1)
        }
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
        data.value = posts.filter { it.id != id }
    }

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        data.value = if (id == 0L) {
            listOf(saved) + posts
        } else {
            posts.map {
                if (it.id != id) it else saved
            }
        }
    }

//    private fun insert(post: Post) {
//        val newListPost = listOf(post.copy(id = ++nextId)) + posts
//        data.value = newListPost
//    }

//   fun update(post: Post) {
//        val newListPost = posts.map {
//            if (it.id == post.id) post else it
//        }
//        data.value = newListPost
//    }

//    private companion object {
//        const val GENERATED_POSTS_AMOUNT = 1000
//    }


}