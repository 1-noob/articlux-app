package com.mecharium.articlux_1.ui.review

sealed class HomeMode {
    object Idle : HomeMode()
    data class Review(val reviewState: ReviewState) : HomeMode()
}