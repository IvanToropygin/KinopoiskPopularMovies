package com.example.kinopoiskpopularmovies.data.mappers

import com.example.kinopoiskpopularmovies.data.remote.models.TrailerDto
import com.example.kinopoiskpopularmovies.domain.TrailerItem

class TrailerMapper {

    // Network → Domain
    fun mapToDomain(networkModel: TrailerDto) = TrailerItem(
        name = networkModel.name ?: "null",
        url = networkModel.url ?: "null"
    )
}