package com.example.googlebooksearchapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestApi {

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/"
        private var bookSearchService: BookSearchService? = null

        fun getSearchedBookSResultInstance(): BookSearchService {
            if (bookSearchService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                bookSearchService = retrofit.create(BookSearchService::class.java)
            }
            return requireNotNull(bookSearchService)
        }
    }
}
