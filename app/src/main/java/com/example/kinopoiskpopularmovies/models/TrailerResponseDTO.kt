package com.example.kinopoiskpopularmovies.models

import com.google.gson.annotations.SerializedName

data class TrailerResponseDTO(@SerializedName("items") val trailersList: List<TrailerDTO>)
