package com.example.kinopoiskpopularmovies.domain.repository

import androidx.lifecycle.LiveData
import com.example.kinopoiskpopularmovies.domain.models.MovieItem
import com.example.kinopoiskpopularmovies.domain.models.TrailerItem

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): List<MovieItem>

    suspend fun getTrailers(kinopoiskId: Int): List<TrailerItem>

    fun getFavoritesMovies(): LiveData<List<MovieItem>>

    suspend fun addMovieToFavourites(movieItem: MovieItem)

    suspend fun getFavouriteMovieById(movieId: Int): MovieItem?

    suspend fun removeMovieFromFavourites(movieId: Int)
}