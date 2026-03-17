package com.mecharium.articlux_1.data.model

import android.os.Message

data class ReviewResponse(
    val status: String,
    val message: String,
    val data: ReviewArticle?,
    val state: String
)

data class ReviewArticle(
    val title: String,
    val url: String
)