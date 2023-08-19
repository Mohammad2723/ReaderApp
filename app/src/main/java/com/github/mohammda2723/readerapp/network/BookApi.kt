package com.github.mohammda2723.readerapp.network

import com.github.mohammda2723.readerapp.model.Book
import com.github.mohammda2723.readerapp.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface BookApi {

    //https://www.googleapis.com/books/v1/volumes?q=android

    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query: String): Book


    @GET("volumes/{bookId}")
    suspend fun getBookInfo(@Path("bookId") bookId: String): Item

}