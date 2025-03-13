package com.example.kinopoiskpopularmovies.domain

class AddMovieToFavoritesUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun getFavoritesMovies(movieItem: MovieItem) {
        moviesRepository.addMovieToFavourites(movieItem)
    }
}