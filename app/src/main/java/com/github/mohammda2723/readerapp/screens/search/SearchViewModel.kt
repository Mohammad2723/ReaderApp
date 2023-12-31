package com.github.mohammda2723.readerapp.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mohammda2723.readerapp.data.Resource
import com.github.mohammda2723.readerapp.model.Item
import com.github.mohammda2723.readerapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {

    var list: List<Item> by mutableStateOf(listOf())

    init {
        loadBook()
    }

    private fun loadBook() {
        searchBook("Android")
    }

     fun searchBook(query: String) {

        viewModelScope.launch(Dispatchers.Default) {

            if (query.isEmpty()) {
                return@launch
            }

            try {

                when (val response = repository.getBooks(query)) {

                    is Resource.Success -> {
                        list = response.data!!
                    }

                    is Resource.Error -> {
                        Log.e("TAG", "searchBook: Failed to gating book")
                    }

                    else -> {}

                }

            } catch (e: Exception) {

                Log.e("TAG", "searchBook: ${e.message}")
            }


        }

    }


}