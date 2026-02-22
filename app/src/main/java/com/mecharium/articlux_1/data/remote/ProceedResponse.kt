package com.mecharium.articlux_1.data.remote

data class ProceedResponse(
    val status: String,
    val message: String,
    val stats: Stats
)

data class Stats(
    val inserted: Int,
    val skipped: Int,
    val total: Int
)