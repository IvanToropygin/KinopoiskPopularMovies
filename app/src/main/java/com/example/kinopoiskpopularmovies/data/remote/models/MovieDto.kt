package com.example.kinopoiskpopularmovies.data.remote.models

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("kinopoiskId") val kinopoiskId: Int,
    @SerializedName("nameRu") val name: String?,
    @SerializedName("nameOriginal") val nameOriginal: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("ratingKinopoisk") val kinopoiskRating: Double?,
    @SerializedName("ratingImdb") val ratingImdb: Double?,
    @SerializedName("year") val year: Int?,
    @SerializedName("posterUrl") val posterUrl: String?,
)
