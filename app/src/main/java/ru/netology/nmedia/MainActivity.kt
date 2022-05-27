package ru.netology.nmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels
import ru.netology.nmedia.activity.PostContentActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.PostContentActivityBinding
import ru.netology.nmedia.dto.PostsAdapter
import ru.netology.nmedia.dto.counterView
import ru.netology.nmedia.util.hideKeyboard
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

        binding.fab.setOnClickListener {
            viewModel.onAddClicked()
//            with(binding.contentEditText) {
//                if(text.isNullOrBlank()) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        "Content can't be empty",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    return@setOnClickListener
//                }
//                val content = text.toString()
//                viewModel.onSaveListener(content)
//
//                clearFocus()
//                hideKeyboard()
//            }
        }

//        viewModel.currentPost.observe(this) { currentPost ->
//            with(binding.contentEditText) {
//                val content = currentPost?.content
//                setText(content)
//                if (content != null) {
//                    requestFocus()
//                    binding.group.visibility = View.VISIBLE
//                    binding.editTextBottom.text = content
//
//                }
//            }
//        }
//        binding.editCancelButton.setOnClickListener {
//            viewModel.currentPost.value = null
//            binding.group.visibility = View.GONE
//            binding.editTextBottom.clearFocus()
//            binding.editTextBottom.hideKeyboard()
//        }

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

        val postContentActivityLauncher = registerForActivityResult(
            PostContentActivity.ResultContract
        ) { postContent ->
            postContent ?: return@registerForActivityResult
            viewModel.onSaveListener(postContent)
        }
        viewModel.navigateToPostContentScreenEvent.observe(this) {
            postContentActivityLauncher.launch()
        }

    }
}