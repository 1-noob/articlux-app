package com.mecharium.articlux_1.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecharium.articlux_1.data.remote.RetrofitInstance
import kotlinx.coroutines.launch


class ReviewViewModel : ViewModel() {

    suspend fun insertPrebuilt(
        articleTitle: String,
        category: String
    ): Boolean {
        return try {
            val response = RetrofitInstance.api.review(
                action = "insert_prebuilt",
                url = articleTitle,
                category = category
            )

            response.isSuccessful
        } catch (e: Exception) {
                false
        }

    }

    suspend fun discardArticle(
        url: String
    ): Boolean {
        return try {
            val response = RetrofitInstance.api.review(
                action = "discard",
                url = url,
                category = null
            )

            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

}