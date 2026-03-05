package com.mecharium.articlux_1.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.currentRecomposeScope

import com.mecharium.articlux_1.data.ArticleRepository.ArticleRepository
import com.mecharium.articlux_1.data.model.Article

class HomeViewModel : ViewModel() {

    private val repository = ArticleRepository()

    private val _articles = mutableStateOf<List<Article>>(emptyList())
    val articles: State<List<Article>> = _articles

    var currentPage = 1
        private set

    fun loadArticles(page: Int) {
        viewModelScope.launch {
            try {
                val data = repository.fetchArticles(page)

                _articles.value = data
                currentPage = page
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun nextPage() {
        loadArticles(currentPage + 1 )
    }

    fun previousPage() {
        if (currentPage > 1){
            loadArticles(currentPage - 1)
        }
    }
}