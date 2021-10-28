package com.example.googlebooksearchapp.model.data

data class BookDataResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)