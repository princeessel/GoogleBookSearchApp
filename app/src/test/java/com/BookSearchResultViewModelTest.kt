package com

import com.example.googlebooksearchapp.viewmodel.BookSearchResultViewModel

import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class BookSearchResultViewModelTest {

    private val viewModel = BookSearchResultViewModel()

    @ExperimentalCoroutinesApi
    @Test
    fun `is search query text  valid`() {
        val authorString = "Prince"
        val keywordString = "Good days"

        val expected = viewModel.validateInputText(authorString, keywordString)

        Assertions.assertTrue(expected)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test empty text fields`() {
        val authorString = ""
        val keywordString = "Good days"

        val expected = viewModel.validateInputText(authorString, keywordString)

        Assertions.assertFalse(expected)

    }
}