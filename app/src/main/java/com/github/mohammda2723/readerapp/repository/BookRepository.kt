package com.github.mohammda2723.readerapp.repository

import com.github.mohammda2723.readerapp.data.DataOrException
import com.github.mohammda2723.readerapp.model.Book
import com.github.mohammda2723.readerapp.model.Item
import com.github.mohammda2723.readerapp.network.BookApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BookApi) {


    private val dataOrException = DataOrException<List<Item>, Boolean, Exception>()
    private val bookInfoDataOrException = DataOrException<Item, Boolean, Exception>()


    suspend fun getBooks(searchQuery: String): DataOrException<List<Item>, Boolean, Exception> {

        /////////////////////////////DataOrException is a data class that we create
        // for manged error loading and data that came from internet or database

        try {

            dataOrException.loading = true
            dataOrException.data = api.getAllBooks(searchQuery).items
            if (dataOrException.data!!.isNotEmpty()) dataOrException.loading = false

        } catch (e: Exception) {

            dataOrException.e = e

        }
        return dataOrException
    }


    suspend fun getBookInfo(bookId: String): DataOrException<Item, Boolean, Exception> {

        try {
            bookInfoDataOrException.loading = true
            bookInfoDataOrException.data = api.getBookInfo(bookId)
            if (bookInfoDataOrException.data.toString().isNotEmpty())
                bookInfoDataOrException.loading = false

        } catch (e: Exception) {
            bookInfoDataOrException.e = e
        }
        return bookInfoDataOrException

    }
}