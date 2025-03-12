package com.example.kinopoiskpopularmovies.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("kinopoiskId") val kinopoiskId: Long,
    @SerializedName("nameRu") val name: String,
    @SerializedName("ratingKinopoisk") val rating: Double = 0.0,
    @SerializedName("year") val year: Int,
    @SerializedName("posterUrl") val posterUrl: String,
) : Parcelable
