package com.example.sessionapplication.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sessionapplication.data.NewsRepository
import com.example.sessionapplication.data.local.dp.ArticleDatabase
import com.example.sessionapplication.data.reomte.model.news.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.nio.file.Files.find

class HomeViewModel : ViewModel() {
    private val newsRepo = NewsRepository()
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    
    init {
        getArticles()
    }

    fun getArticles() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            kotlin.runCatching {
                val remoteList = newsRepo.getNews("bitcoins")
                remoteList.onEach { it.isInsert = isArticleAlreadySaved(it) }
                _state.update {
                    it.copy(isLoading = false, list = newsRepo.getNews("bitCoin"))
                }
            }.getOrElse { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        dialogModel = DialogModel(error.message.toString(), true)
                    )
                }
            }
        }
    }

    private suspend fun isArticleAlreadySaved(article: Article): Boolean {
        return newsRepo.getAllArticle().first().find {
            it.title == article.title
        } != null

    }

    fun insertArticle(article: Article) {
        viewModelScope.launch {
            if (isArticleAlreadySaved(article)) return@launch
            newsRepo.insertNews(article)
            Log.d("OnInsert", article.id.toString())
        }
    }


    fun dismissDialog() {
        _state.update {
            it.copy(dialogModel = null)
        }
    }

}


data class HomeState(
    val isLoading: Boolean = true,
    val list: List<Article> = emptyList(),
    val dialogModel: DialogModel? = null
)

data class DialogModel(
    val message: String,
    val isShouldShow: Boolean = false
)