package com.mecharium.articlux_1.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

import com.mecharium.articlux_1.data.model.Article
import com.mecharium.articlux_1.data.remote.RetrofitInstance.api
import android.util.Log
import com.mecharium.articlux_1.data.ArticleRepository.ArticleRepository

class HomeViewModel : ViewModel() {

    private val repository = ArticleRepository()
    private val _articles = mutableStateOf<List<Article>>(emptyList())
    val articles: State<List<Article>> = _articles

    var currentPage = 1
        private set

    fun loadArticles(page: Int) {
        viewModelScope.launch {
            try {
                val articles = repository.fetchArticles(page)

                Log.d("API_DEBUG", "${articles.size}")

                currentPage = page
                _articles.value = articles
            } catch (e: Exception){
                Log.e("API_DEBUG", "Error loading articles", e)
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