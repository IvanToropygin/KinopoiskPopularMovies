package com.example.kinopoiskpopularmovies.domain

class GetFavoritesMoviesUseCase(private val moviesRepository: MoviesRepository) {

    fun getFavoritesMovies() {
        moviesRepository.getFavoritesMovies()
    }
}