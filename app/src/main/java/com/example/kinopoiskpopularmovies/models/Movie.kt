package com.example.kinopoiskpopularmovies.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favourite_movies")
@Parcelize
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("kinopoiskId") val kinopoiskId: Long,

    @ColumnInfo(name = "name")
    @SerializedName("nameRu") val name: String,

    @ColumnInfo(name = "description")
    @SerializedName("description") val description: String,

    @ColumnInfo(name = "countries")
    @SerializedName("countries") val countries: List<CountryDto>,

    @ColumnInfo(name = "rating")
    @SerializedName("ratingKinopoisk") val rating: Double = 0.0,

    @ColumnInfo(name = "year")
    @SerializedName("year") val year: Int,

    @ColumnInfo(name = "posterUrl")
    @SerializedName("posterUrl") val posterUrl: String,
) : Parcelable
