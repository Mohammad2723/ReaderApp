package com.github.mohammda2723.readerapp.model

data class Offer(
    val finskyOfferType: Int,
    val giftable: Boolean,
    val listPrice: ListPriceX,
    val retailPrice: RetailPrice
)