package com.example.newsify.Network

import com.example.newsify.model.api.NewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?excludeDomains=google.com&q=tech%20OR%20business%20OR%20apple%20OR%20politics&pageSize=10&apiKey=3b85bf1206fe4f85a5ee506ada712898&page=1&to=2023-07-11
const val BASE_URL = "https://newsapi.org"
const val API_KEY_ = "3ee56bd870144c0ca0a0783091596a4d"
const val API_KEY__ = "3b85bf1206fe4f85a5ee506ada712898"
const val API_KEY = "2eb60f0e77df47398634909d96812e04"

interface NewsInterface {
    @GET("/v2/everything?excludeDomains=google.com&q=tech%20OR%20business%20OR%20apple%20OR%20politics&pageSize=10&apiKey=$API_KEY")
    fun getNewsItem(@Query("page") page: Int, @Query("to") to:String) : Call<NewsResponse>
}
object NewsService {

    val newsInstance: NewsInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsInterface::class.java)
    }
}
