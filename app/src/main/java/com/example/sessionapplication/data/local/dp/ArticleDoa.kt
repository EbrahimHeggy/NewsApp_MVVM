package com.example.sessionapplication.data.local.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.sessionapplication.data.reomte.model.news.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM article")
    fun getAllArticle(): Flow<List<Article>>

    @Query("DELETE FROM article")
    suspend fun deleteAllArticle()

    @Query("DELETE FROM article WHERE id = :articleId")
    suspend fun deleteArticle(articleId: Int)

}