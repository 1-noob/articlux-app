package com.mecharium.articlux_1.data.ArticleRepository

import com.mecharium.articlux_1.data.model.Article
import com.mecharium.articlux_1.data.remote.RetrofitInstance

class ArticleRepository {

    suspend fun fetchArticles(page: Int): List<Article>{
        val response = RetrofitInstance.api.getArticles(page)
        return response.articles
    }

}