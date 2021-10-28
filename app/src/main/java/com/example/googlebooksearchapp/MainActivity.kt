package com.example.googlebooksearchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlebooksearchapp.databinding.ActivityMainBinding
import com.example.googlebooksearchapp.model.RestApi
import com.example.googlebooksearchapp.model.repository.Repository
import com.example.googlebooksearchapp.view.BookSearchResultAdapter
import com.example.googlebooksearchapp.viewmodel.BookSearchResultViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val bookService = RestApi.getSearchedBookSResultInstance()
    private val repository = Repository(bookService)
    private val bookSearchResultAdapter= BookSearchResultAdapter()
    private lateinit var progressBar: ProgressBar


    private val viewModel by viewModels<BookSearchResultViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        progressBar = binding.spinner.progressBar

        setContentView(binding.root)

        binding.recyclerview.adapter = bookSearchResultAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        initViewModel()
        initObservers()
        onSearchButtonClicked()
    }

    private fun initViewModel() {
        viewModel.init(repository = repository)
    }

    private fun onSearchButtonClicked() {
        progressBar.visibility = GONE
        binding.button.setOnClickListener{
            getBookSearchResult()
        }

    }



    private fun getBookSearchResult() {
        val authorString = binding.authorString.text.toString()
        val keywordString = binding.keywordString.text.toString()

        viewModel.getBookSearchResult(authorString, keywordString)
    }

    private fun initObservers() {
        viewModel.bookSearchResultsResponse.observe(this, {
            bookSearchResultAdapter.setBooks(it.items)
        })

        viewModel.validInputText.observe(this, {
            Toast.makeText(this, "Please Enter Author or Keyword", Toast.LENGTH_LONG).show()
        })

        viewModel.isLoading.observe(this, {
            if (it) {
                progressBar.visibility = VISIBLE
                binding.recyclerview.visibility = GONE
            } else {
                progressBar.visibility = GONE
                binding.recyclerview.visibility = VISIBLE
            }
        })
    }
}
