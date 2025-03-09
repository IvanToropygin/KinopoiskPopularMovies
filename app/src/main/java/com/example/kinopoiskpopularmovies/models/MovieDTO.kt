package com.example.kinopoiskpopularmovies.models

import com.google.gson.annotations.SerializedName

data class MovieDTO(@SerializedName("kinopoiskId") val kinopoiskId: Long)
