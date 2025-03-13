package com.example.kinopoiskpopularmovies.domain

class GetFavoriteMovieByIdUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun getFavoriteMovieById(movieId: Int): MovieItem? {
        return moviesRepository.getFavouriteMovieById(movieId)
    }
}