package com.example.kinopoiskpopularmovies.data.network.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("items") val movies: List<MovieDto>
)
