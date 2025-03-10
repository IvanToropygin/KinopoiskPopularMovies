package com.example.kinopoiskpopularmovies.models

import com.google.gson.annotations.SerializedName

data class CountryDto(@SerializedName("country") val country: String? = null)
