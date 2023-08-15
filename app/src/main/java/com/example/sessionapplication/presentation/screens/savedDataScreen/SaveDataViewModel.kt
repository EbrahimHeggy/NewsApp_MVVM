package com.example.sessionapplication.presentation.screens.savedDataScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sessionapplication.data.NewsRepository
import com.example.sessionapplication.data.local.dp.ArticleDatabase
import com.example.sessionapplication.data.reomte.model.news.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SaveDataViewModel : ViewModel() {
   private val newRepo = NewsRepository()
    private val _state = MutableStateFlow(SaveDataState())
    val state = _state.asStateFlow()

    init {
        getList()
    }

    fun getList() {
        viewModelScope.launch {
            newRepo.getAllArticle().collectLatest {list->
                _state.update {
                    it.copy(list = list)
                }
            }

        }
    }



    fun deleteAll(){
        viewModelScope.launch {
            newRepo.deleteAllArticle()
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            newRepo.deleteArticle(article)
        }
    }

}

data class SaveDataState(
    val list: List<Article> = emptyList()
)