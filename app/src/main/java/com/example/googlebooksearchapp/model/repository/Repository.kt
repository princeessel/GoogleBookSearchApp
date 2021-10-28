package com.example.googlebooksearchapp.model.repository

import com.example.googlebooksearchapp.model.BookSearchService

class Repository(private val bookSearchService: BookSearchService) {

    suspend fun getBookSearchResult(
        filters: HashMap<String, String>
    ) = bookSearchService.getBookSearchResult(filters)
}
