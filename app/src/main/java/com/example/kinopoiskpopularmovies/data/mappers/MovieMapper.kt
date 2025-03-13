package com.example.kinopoiskpopularmovies.data.mappers

import com.example.kinopoiskpopularmovies.data.MovieItemDBModel
import com.example.kinopoiskpopularmovies.models.Movie

class MovieMapper {
    fun mapEntityToDbModel(movieItem: Movie) = MovieItemDBModel(
        id = movieItem.kinopoiskId,
        name = movieItem.name,
        description = movieItem.description,
        rating = movieItem.rating,
        year = movieItem.year,
        posterUrl = movieItem.posterUrl
    )

    fun mapDbModelToEntity(shopItemDBModel: MovieItemDBModel) = Movie(
        kinopoiskId = shopItemDBModel.id,
        name = shopItemDBModel.name,
        description = shopItemDBModel.description,
        rating = shopItemDBModel.rating,
        year = shopItemDBModel.year,
        posterUrl = shopItemDBModel.posterUrl
    )
}