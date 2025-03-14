package com.example.kinopoiskpopularmovies.data.remote.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("items") val movies: List<MovieDto>
)
