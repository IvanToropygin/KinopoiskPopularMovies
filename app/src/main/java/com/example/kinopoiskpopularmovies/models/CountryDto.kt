package com.example.kinopoiskpopularmovies.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDto(@SerializedName("country") val country: String) : Parcelable
