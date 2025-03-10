package com.example.kinopoiskpopularmovies.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("items") val movies: List<Movie>,
    @SerializedName("totalPages") val totalPages: Int
)
