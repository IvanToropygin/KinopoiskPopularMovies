package com.example.kinopoiskpopularmovies.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
    val kinopoiskId: Int,
    val name: String,
    val description: String,
    val rating: Double,
    val year: Int,
    val posterUrl: String,
    val isFavorite: Boolean = false // add to favourites
) : Parcelable