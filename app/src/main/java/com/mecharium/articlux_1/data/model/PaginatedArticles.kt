package com.mecharium.articlux_1.data.model

data class PaginatedArticles(
    val status: String,
    val page: Int,
    val limit: Int,
    val count: Int,
    val articles: List<Article>
)