package com.example.kinopoiskpopularmovies.models

import com.google.gson.annotations.SerializedName

data class ReviewResponseDTO(@SerializedName("items") val reviews: List<ReviewDTO>)
