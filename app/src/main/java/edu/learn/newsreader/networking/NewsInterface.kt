package edu.learn.newsreader.networking

import edu.learn.newsreader.modals.NewsStatus
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {
    @GET("top-headlines")
    fun getNews(

        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?

    ): Call<NewsStatus?>?
}
