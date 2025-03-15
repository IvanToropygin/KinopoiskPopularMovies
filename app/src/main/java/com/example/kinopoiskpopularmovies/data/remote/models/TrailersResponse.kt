package com.example.kinopoiskpopularmovies.data.remote.models

import com.google.gson.annotations.SerializedName

data class TrailersResponse(
    @SerializedName("items") val trailers: List<TrailerDto>
)
