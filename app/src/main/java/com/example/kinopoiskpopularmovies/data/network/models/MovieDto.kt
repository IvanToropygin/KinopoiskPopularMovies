package com.example.kinopoiskpopularmovies.data.network.models

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("kinopoiskId") val kinopoiskId: Int,
    @SerializedName("nameRu") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("ratingKinopoisk") val rating: Double?,
    @SerializedName("year") val year: Int?,
    @SerializedName("posterUrl") val posterUrl: String?,
)
