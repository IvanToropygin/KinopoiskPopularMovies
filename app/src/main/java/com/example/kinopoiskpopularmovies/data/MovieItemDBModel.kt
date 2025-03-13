package com.example.kinopoiskpopularmovies.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class MovieItemDBModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val rating: Double = 0.0,
    val year: Int,
    val posterUrl: String,
)
