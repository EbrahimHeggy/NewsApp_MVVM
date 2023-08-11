package com.example.sessionapplication.data.local.dp

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sessionapplication.data.reomte.model.news.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDoa(): ArticleDoa

    companion object {

        private lateinit var instance: ArticleDatabase
        private val Lock = Any()
        fun Application.initArticleDataBase(): ArticleDatabase {
            return runCatching { instance }.getOrElse {
                synchronized(Lock) {
                    createDatabase(this).also { instance = it }
                }
            }


        }

        fun getInstance() = instance

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()

    }
}