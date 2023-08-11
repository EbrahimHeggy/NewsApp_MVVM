package com.example.sessionapplication.data

import com.example.sessionapplication.data.local.dp.ArticleDatabase
import com.example.sessionapplication.data.reomte.RetrofitClient
import com.example.sessionapplication.data.reomte.api.NewsApi
import com.example.sessionapplication.data.reomte.model.news.model.Article
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val api: NewsApi = RetrofitClient().newsApi,
    private val db: ArticleDatabase = ArticleDatabase.getInstance()
) {


    suspend fun getNews(q: String): List<Article> {
        val result = api.getNews(q)
        return result.body()?.articles.orEmpty()
    }

    suspend fun insertNews(article: Article) {

        return db.articleDoa().insert(article)
    }

    fun getAllArticle(): Flow<List<Article>> = db.articleDoa().getAllArticle()

    suspend fun deleteAllArticle() = db.articleDoa().deleteAllArticle()

}