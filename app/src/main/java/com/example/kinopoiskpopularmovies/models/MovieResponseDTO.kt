package com.example.kinopoiskpopularmovies.models

import com.google.gson.annotations.SerializedName

data class MovieResponseDTO(@SerializedName("items") val movies: List<MovieDTO>)
