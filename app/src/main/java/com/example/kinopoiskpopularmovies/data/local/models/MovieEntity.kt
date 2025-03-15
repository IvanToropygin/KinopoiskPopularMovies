package com.example.kinopoiskpopularmovies.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "original_name")
    val nameOriginal: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "rating")
    val rating: Double?,

    @ColumnInfo(name = "rating_imdb")
    val ratingImdb: Double?,

    @ColumnInfo(name = "year")
    val year: Int?,

    @ColumnInfo(name = "poster_url")
    val posterUrl: String?
)
