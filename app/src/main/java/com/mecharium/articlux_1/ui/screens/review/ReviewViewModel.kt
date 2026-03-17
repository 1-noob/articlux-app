package com.mecharium.articlux_1.ui.screens.review

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecharium.articlux_1.data.model.ReviewArticle
import kotlinx.coroutines.launch

import com.mecharium.articlux_1.data.remote.RetrofitInstance
import com.mecharium.articlux_1.data.model.ReviewResponse


class ReviewViewModel : ViewModel() {

    var article = mutableStateOf<ReviewArticle?>(null)
        private set

    var loading = mutableStateOf(false)
        private set

    var message = mutableStateOf("Loading Article...")
        private set

    var reviewState = mutableStateOf("")
        private set

    fun loadNext() {
        viewModelScope.launch {
            try {
                loading.value = true

                val response = RetrofitInstance.api.review(
                    action = "get_next"
                )

                if (response.isSuccessful) {
                    val body = response.body()

                    article.value = body?.data
                    reviewState.value = body?.state ?: ""

                    if (article.value == null) {
                        message.value = "Review complete.!"
                    }

                } else {
                    message.value = "Review Failed!\n ${response.code()}"
                }
            } catch (e: Exception) {
                message.value = "Review Failed!\n ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    private fun performAction(
        action: String,
        url: String,
        category: String? = null
    ) {

        viewModelScope.launch {
            try {
                loading.value = true

                val response = RetrofitInstance.api.review(
                    action=action,
                    url = url,
                    category = category
                )

                if(response.isSuccessful) {
                    loadNext()
                } else {
                    message.value = "Action Failed!\n ${response.code()}"
                }
            } catch (e: Exception) {
                message.value = "Action Failed!\n ${e.message}"
            } finally {
                loading.value = false
            }
        }

    }

    // Discard Articles
    fun discard(url: String) {
        performAction(
            action = "discard",
            url = url
        )
    }

    // Insert Article (with one of the existing categories)
    fun insertPrebuilt(url: String, category: String) {
        performAction(
            action = "insert_prebuilt",
            url = url,
            category = category
        )
    }

    // Insert Article (with a special category)
    fun insertSpecial(url: String) {
        performAction(
            action = "insert_special",
            url= url,
            category = "Inserted manually"
        )
    }

}