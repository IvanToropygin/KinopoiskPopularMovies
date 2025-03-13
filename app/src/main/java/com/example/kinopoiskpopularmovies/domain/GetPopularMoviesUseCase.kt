package com.example.kinopoiskpopularmovies.domain

import androidx.lifecycle.LiveData

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun getPopularMovies(page: Int): List<MovieItem> {
        return moviesRepository.getPopularMovies(page)
    }
}