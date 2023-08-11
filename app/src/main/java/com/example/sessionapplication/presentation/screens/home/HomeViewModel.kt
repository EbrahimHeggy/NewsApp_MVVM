package com.example.sessionapplication.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sessionapplication.data.NewsRepository
import com.example.sessionapplication.data.local.dp.ArticleDatabase
import com.example.sessionapplication.data.reomte.model.news.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
                newsRepo.getNews("bitcoins")
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


    fun insertArticle(article: Article) {
        viewModelScope.launch {
            val x = newsRepo.insertNews(article)
            Log.i("OnInsert", article.id.toString())
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