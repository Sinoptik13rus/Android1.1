package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.activity.PostContentActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.PostsAdapter
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()

        val adapter = PostsAdapter(viewModel)
        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        val postContentActivityLauncher = registerForActivityResult(
            PostContentActivity.ResultContract
        ) { postContent ->
            postContent ?: return@registerForActivityResult
            viewModel.onSaveListener(postContent)
        }

        binding.fab.setOnClickListener {
            viewModel.onAddClicked()

            viewModel.repostPostContent.observe(this) { postContent ->
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, postContent)
                    type = "text/plain"
                }

                val repostIntent =
                    Intent.createChooser(
                        intent, getString(R.string.chooser_share_post)
                    )
                startActivity(repostIntent)
            }



            viewModel.navigateToPostContentScreenEvent.observe(this) {
                val content = viewModel.currentPost.value?.content
                postContentActivityLauncher.launch(content)
            }
        }
        viewModel.videoUrl.observe(this) { link ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}
