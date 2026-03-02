package com.mecharium.articlux_1.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecharium.articlux_1.data.remote.RetrofitInstance
import kotlinx.coroutines.launch


class ReviewViewModel : ViewModel() {

    fun insertPrebuilt(
        articleTitle: String,
        category: String
    ) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.review(
                    action = "insert_prebuilt",
                    url = articleTitle,
                    category = category
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}