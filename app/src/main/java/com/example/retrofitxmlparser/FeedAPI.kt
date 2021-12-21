package com.example.retrofitxmlparser

import retrofit2.http.GET
import com.example.retrofitxmlparser.Feed
import retrofit2.Call

interface FeedAPI {
    @get:GET("dogs/.rss")
    val feed: Call<Feed?>?

    companion object {
        const val BASE_URL = "https://www.reddit.com/r/"
    }
}