package com.github.mohammda2723.readerapp.data

data class DataOrException<T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: kotlin.Boolean? = null,
    var e: E? = null
)
