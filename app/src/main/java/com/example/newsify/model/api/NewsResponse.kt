package com.example.newsify.model.api

data class NewsResponse(
    val totalResults : Int,
    val articles : List<NewsItemRetrofit>
)
