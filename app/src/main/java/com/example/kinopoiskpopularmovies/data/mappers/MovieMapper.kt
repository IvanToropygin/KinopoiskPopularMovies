package com.example.kinopoiskpopularmovies.data.mappers

import com.example.kinopoiskpopularmovies.data.local.models.MovieEntity
import com.example.kinopoiskpopularmovies.data.remote.models.MovieDto
import com.example.kinopoiskpopularmovies.domain.MovieItem

class MovieMapper {

    // Network → Domain
    fun mapToDomain(networkModel: MovieDto) = MovieItem(
        kinopoiskId = networkModel.kinopoiskId,
        name = networkModel.name ?: "name is null",
        description = networkModel.description ?: "description is null",
        rating = networkModel.rating ?: 0.0,
        year = networkModel.year ?: 0,
        posterUrl = networkModel.posterUrl ?: "",
        isFavorite = false // by default
    )

    // Database → Domain
    fun mapToDomain(entity: MovieEntity) = MovieItem(
        kinopoiskId = entity.id,
        name = entity.name,
        description = entity.description ?: "description is null",
        rating = entity.rating ?: 0.0,
        year = entity.year ?: 0,
        posterUrl = entity.posterUrl ?: "",
        isFavorite = true // in database
    )

    // Domain → Database
    fun mapToEntity(domainModel: MovieItem) = MovieEntity(
        id = domainModel.kinopoiskId,
        name = domainModel.name,
        description = domainModel.description,
        rating = domainModel.rating,
        year = domainModel.year,
        posterUrl = domainModel.posterUrl
    )

    // Database → Domain
    fun mapToDomain(list: List<MovieEntity>) = list.map { itemDBModel ->
        mapToDomain(itemDBModel)
    }
}