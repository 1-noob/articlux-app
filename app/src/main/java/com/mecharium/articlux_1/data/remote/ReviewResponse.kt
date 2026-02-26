package com.mecharium.articlux_1.data.remote

data class ReviewResponse(
    val status: String,
    val message: String?,
    val data: ReviewData?,
    val state: String
)

data class ReviewData(
    val url: String,
    val title: String
)