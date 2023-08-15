package com.example.sessionapplication.data.reomte.api

import com.example.sessionapplication.data.reomte.model.news.model.Article
import com.example.sessionapplication.data.reomte.model.news.model.ArticleNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    companion object{
        const val API_KEY = "e707a202ea3e49788eca25dce2b74a15"
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