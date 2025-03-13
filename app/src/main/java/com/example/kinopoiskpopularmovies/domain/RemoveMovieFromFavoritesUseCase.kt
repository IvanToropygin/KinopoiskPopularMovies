package com.example.kinopoiskpopularmovies.domain

class RemoveMovieFromFavoritesUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun getFavoritesMovies(movieId: Int) { moviesRepository.removeMovieFromFavourites(movieId) }
}