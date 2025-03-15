package com.example.kinopoiskpopularmovies.data.mappers

import com.example.kinopoiskpopularmovies.data.local.models.MovieEntity
import com.example.kinopoiskpopularmovies.data.remote.models.MovieDto
import com.example.kinopoiskpopularmovies.domain.models.MovieItem

class MovieMapper {

    // Network → Domain
    fun mapToDomain(networkModel: MovieDto) = MovieItem(
        kinopoiskId = networkModel.kinopoiskId,
        name = networkModel.name ?: "null",
        nameOriginal = networkModel.nameOriginal ?: "null",
        imdbRating = networkModel.ratingImdb ?: 0.0,
        description = networkModel.description ?: "null",
        kinopoiskRating = networkModel.kinopoiskRating ?: 0.0,
        year = networkModel.year ?: 0,
        posterUrl = networkModel.posterUrl ?: "null",
        isFavorite = false // by default
    )

    // Database → Domain
    fun mapToDomain(entity: MovieEntity) = MovieItem(
        kinopoiskId = entity.id,
        name = entity.name,
        nameOriginal = entity.nameOriginal,
        description = entity.description ?: "null",
        kinopoiskRating = entity.rating ?: 0.0,
        imdbRating = entity.ratingImdb ?: 0.0,
        year = entity.year ?: 0,
        posterUrl = entity.posterUrl ?: "",
        isFavorite = true // in database
    )

    // Domain → Database
    fun mapToEntity(domainModel: MovieItem) = MovieEntity(
        id = domainModel.kinopoiskId,
        name = domainModel.name,
        nameOriginal = domainModel.nameOriginal,
        description = domainModel.description,
        rating = domainModel.kinopoiskRating,
        ratingImdb = domainModel.imdbRating,
        year = domainModel.year,
        posterUrl = domainModel.posterUrl
    )

    // Database → Domain
    fun mapToDomain(list: List<MovieEntity>) = list.map { itemDBModel ->
        mapToDomain(itemDBModel)
    }
}