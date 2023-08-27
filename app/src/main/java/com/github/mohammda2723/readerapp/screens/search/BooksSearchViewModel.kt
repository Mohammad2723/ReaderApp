package com.github.mohammda2723.readerapp.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mohammda2723.readerapp.model.Item
import com.github.mohammda2723.readerapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BooksSearchViewModel @Inject constructor(private val repository: BookRepository) :
    ViewModel() {

    var list: List<Item> by mutableStateOf(listOf())

    init {
        loadBook()
    }

    private fun loadBook() {
        searchBooks("android")
    }


    private fun searchBooks(query: String) {

        viewModelScope.launch(Dispatchers.Default) {

            if (query.isEmpty()) {
                return@launch
            }

            try {
//            when(val response = repository.getBooks(query))



            }catch (e:Exception){

            }

        }


    }


}