package com.github.mohammda2723.readerapp.repository

import com.github.mohammda2723.readerapp.data.Resource
import com.github.mohammda2723.readerapp.model.Item
import com.github.mohammda2723.readerapp.network.BookApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BookApi) {


    suspend fun getBooks(searchQuery: String): Resource<List<Item>> {

        /////////////////////////////Resource is a sealed  class that we create
        // for manged error loading and data that came from internet or database

        return try {

            Resource.Loading(data = true)
            val itemList = api.getAllBooks(searchQuery).items
            if (itemList.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data = itemList)

        } catch (exception: Exception) {

            Resource.Error(massage = exception.message.toString())
        }


    }


    suspend fun getBook(bookId: String): Resource<Item> {

        val response = try {
            Resource.Loading(data = true)
            api.getBookInfo(bookId)

        } catch (e: Exception) {

            return Resource.Error(massage = e.message.toString())
        }

        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }
}