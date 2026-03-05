package com.mecharium.articlux_1.data.model

data class Article (
    var title: String,
    val status: String,
    val rating: Int,
    val category: String,
    val url: String
)