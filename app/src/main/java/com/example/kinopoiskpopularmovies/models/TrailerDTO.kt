package com.example.kinopoiskpopularmovies.models

import com.google.gson.annotations.SerializedName

data class TrailerDTO(
    @SerializedName("name") val trailerName: String,
    @SerializedName("url") val trailerUrl: String,
)
