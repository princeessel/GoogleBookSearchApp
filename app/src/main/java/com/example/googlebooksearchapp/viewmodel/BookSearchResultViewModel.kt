package com.example.googlebooksearchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebooksearchapp.model.data.BookDataResponse
import com.example.googlebooksearchapp.model.repository.Repository
import kotlinx.coroutines.launch

class BookSearchResultViewModel: ViewModel() {
    private lateinit var repository: Repository

    private val _bookSearchResults =  MutableLiveData<BookDataResponse>()
    val bookSearchResultsResponse: LiveData<BookDataResponse> =  _bookSearchResults

    private val _validInputText =  MutableLiveData<Boolean>()
    val validInputText: LiveData<Boolean> =  _validInputText

    private val _isLoading =  MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> =  _isLoading

    fun init(repository: Repository) {
        this.repository = repository
    }

    fun getBookSearchResult(authorString: String, keywordString: String) {
        if (!validateInputText(authorString, keywordString)) {
            _validInputText.postValue(false)
            return
        }

        _isLoading.value = true

        val filters = HashMap<String, String>()
        filters["q"] = authorString
        filters["inauthor"] = keywordString

        viewModelScope.launch {
            val response = repository.getBookSearchResult(filters)

            if (!response.isSuccessful) {
                return@launch
            }

            _bookSearchResults.postValue(response.body())

            _isLoading.value = false
        }
    }

    private fun validateInputText(authorString: String, keywordString: String): Boolean {
        return !(authorString.isEmpty() || keywordString.isEmpty())
    }
}
