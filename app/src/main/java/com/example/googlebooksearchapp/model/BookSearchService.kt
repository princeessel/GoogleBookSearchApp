package com.example.googlebooksearchapp.model

import com.example.googlebooksearchapp.model.data.BookDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface BookSearchService {
    @GET("books/v1/volumes")
    suspend fun getBookSearchResult(
        @QueryMap query: HashMap<String, String>
    ): Response<BookDataResponse>
}
