package com.mecharium.articlux_1.data.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    var title: String?,

    @SerializedName("category")
    val category: String?,

    @SerializedName("rating")
    val rating: Int?,

    @SerializedName("status")
    val status: Int?,

    @SerializedName("url")
    val url: String?
)