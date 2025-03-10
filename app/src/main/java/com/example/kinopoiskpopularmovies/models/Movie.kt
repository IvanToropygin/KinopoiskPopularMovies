package com.example.kinopoiskpopularmovies.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("kinopoiskId") val kinopoiskId: Long,
    @SerializedName("nameRu") val name: String,
    @SerializedName("ratingKinopoisk") val rating: Double = 0.0,
    @SerializedName("year") val year: Int,
    @SerializedName("posterUrl") val posterUrl: String,
)
