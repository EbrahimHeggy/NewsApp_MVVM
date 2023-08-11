package com.example.sessionapplication.data.reomte.api

import com.example.sessionapplication.data.reomte.model.news.model.Article
import com.example.sessionapplication.data.reomte.model.news.model.ArticleNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    companion object{
        const val API_KEY = "11a594b1e18a4e1b859144846fd7ba01"
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("everything")
    suspend fun getNews (
        @Query("q")
         query : String,
        @Query("apiKey")
        apiKey : String = API_KEY
    ) : Response<ArticleNews>
}