package com.miu.foodieapp.ui.blog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.foodieapp.R
import com.miu.foodieapp.adapter.BlogAdapter
import com.miu.foodieapp.databinding.FragmentBlogBinding
import com.miu.foodieapp.model.Blog

class BlogFragment : Fragment() {

    private lateinit var binding: FragmentBlogBinding

    private val blogList = ArrayList<Blog>(listOf(
        Blog(
            "Exploring the Cosmos",
            "Carl Sagan",
            "The wonders of the universe continue to captivate us as we delve into the mysteries of space. From distant galaxies to the search for extraterrestrial life, our journey to understand the cosmos is ongoing."
        ),
        Blog(
            "Artificial Intelligence Revolution",
            "Alan Turing",
            "Advancements in artificial intelligence are reshaping the way we live and work. From machine learning to neural networks, the AI revolution is paving the way for innovative solutions and unprecedented possibilities."
        )
    ))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlogBinding.inflate(layoutInflater)

        val recyclerView = binding.blogRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = BlogAdapter(blogList)
        recyclerView.adapter = adapter

        binding.btnAddNewBlogPost.setOnClickListener { addNewBlogPostClicked(adapter) }

        return binding.root
    }

    private fun addNewBlogPostClicked(blogAdapter: BlogAdapter) {
        val addBlogFormBuilder = AlertDialog.Builder(requireContext())
        addBlogFormBuilder.setTitle("Add new blog post")
        val addBlogFormView = layoutInflater.inflate(R.layout.add_blog_form, null)
        addBlogFormBuilder.setView(addBlogFormView)

        addBlogFormBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        addBlogFormBuilder.setPositiveButton("Save") { dialog, _ ->
            val sharedPreferences = requireContext().getSharedPreferences("LoggedInUserPreferences", Context.MODE_PRIVATE)

            val authorName = sharedPreferences.getString("displayName", "Anonymous")
            val title = addBlogFormView.findViewById<EditText>(R.id.newBlogTitle).text.toString()
            val content = addBlogFormView.findViewById<EditText>(R.id.newBlogContent).text.toString()

            val newBlog = Blog(
                title,
                authorName,
                content
            )

            blogList.add(0, newBlog)
            blogAdapter.notifyDataSetChanged()
            dialog.dismiss()

            Toast.makeText(
                requireContext(),
                "Blog added successfully",
                Toast.LENGTH_SHORT
            ).show()
        }

        val blogDialog = addBlogFormBuilder.create()
        blogDialog.show()
    }

}