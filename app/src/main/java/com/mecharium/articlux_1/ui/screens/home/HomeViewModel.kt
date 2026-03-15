package com.mecharium.articlux_1.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import kotlinx.coroutines.launch
import android.util.Log

import com.mecharium.articlux_1.data.model.Article
import com.mecharium.articlux_1.data.repository.ArticleRepository


class HomeViewModel : ViewModel() {

    private val repository = ArticleRepository()

    private val _articles = mutableStateOf<List<Article>>(emptyList())
    val articles: State<List<Article>> = _articles

    private val _currentPage = mutableStateOf(1)
    val currentPage: State<Int> = _currentPage

    fun load_Articles(page:Int) {
        viewModelScope.launch {
            try {
                val articles = repository.fetchArticles(page)

                Log.d("API DEBUG", "Articles : ${articles.size}")

                _currentPage.value = page
                _articles.value = articles
            } catch (e: Exception) {
                Log.d("API DEBUG", "Error loading articles", e)
            }
        }
    }

    fun nextPage() {
        load_Articles(_currentPage.value + 1)
    }

    fun previousPage() {
        if (_currentPage.value > 1){
            load_Articles(_currentPage.value - 1)
        }
    }

}