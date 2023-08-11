package com.example.sessionapplication

import android.app.Application
import com.example.sessionapplication.data.local.dp.ArticleDatabase
import com.example.sessionapplication.data.local.dp.ArticleDatabase.Companion.initArticleDataBase

class SessionApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initArticleDataBase()
    }
}