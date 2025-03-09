package com.example.kinopoiskpopularmovies.models

import com.google.gson.annotations.SerializedName

data class ReviewDTO(
    @SerializedName("type") val type: String,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val content: String,
)
