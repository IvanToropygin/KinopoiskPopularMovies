package com.example.kinopoiskpopularmovies.domain

import androidx.lifecycle.LiveData

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): List<MovieItem>

    fun getFavoritesMovies(): LiveData<List<MovieItem>>

    suspend fun getFavouriteMovieById(movieId: Int): MovieItem?

    suspend fun addMovieToFavourites(movieItem: MovieItem)

    suspend fun removeMovieFromFavourites(movieId: Int)
}