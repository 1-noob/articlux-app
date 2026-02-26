package com.mecharium.articlux_1.ui.review

import com.mecharium.articlux_1.data.model.ReviewArticle

sealed class ReviewState {

    data class ShowingArticle(
        val article: ReviewArticle
    ) : ReviewState()

    data class SelectingCategory(
        val article: ReviewArticle,
        val inputCategory: String = ""
    ) : ReviewState()

    data class ConfirmInsert(
        val article: ReviewArticle,
        val category: String
    ) : ReviewState()

    data class ConfirmSpecial(
        val article: ReviewArticle
    ) : ReviewState()

    data class ConfirmDiscard(
        val article: ReviewArticle
    ) : ReviewState()

    data class Loading(
        val article: ReviewArticle
    ) : ReviewState()

    data class Error(
        val message: String,
        val article: ReviewArticle? = null
    ) : ReviewState()
}