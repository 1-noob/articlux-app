package com.mecharium.articlux_1.data.model

import com.google.gson.annotations.SerializedName

data class Article (

    @SerializedName("Article Title")
    var title: String?,

    @SerializedName("Category")
    val category: String?,

    @SerializedName("Rating")
    val rating: Int?,

    @SerializedName("Status")
    val status: Int?,

    @SerializedName("URL")
    val url: String?
)