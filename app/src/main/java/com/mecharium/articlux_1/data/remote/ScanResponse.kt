package com.mecharium.articlux_1.data.remote

data class ScanResponse(
    val status: String,
    val message: String,
    val stats: ScanStats
)

data class ScanStats(
    val rss_entries: Int,
    val skipped_quizzes: Int,
    val skipped_duplicates: Int,
    val classified: Int,
    val needs_review: Int,
    val new_articles: Int
)