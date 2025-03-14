package com.example.kinopoiskpopularmovies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.kinopoiskpopularmovies.data.local.FavouriteMoviesDao
import com.example.kinopoiskpopularmovies.data.mappers.MovieMapper
import com.example.kinopoiskpopularmovies.data.remote.MovieApi
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.domain.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val moviesDao: FavouriteMoviesDao,
    private val mapper: MovieMapper
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): List<MovieItem> {
        return withContext(Dispatchers.IO) {
            api.getPopularMovies(page).movies
        }.map { mapper.mapToDomain(it) }
    }

    override fun getFavoritesMovies(): LiveData<List<MovieItem>> {
        return moviesDao.getAllFavouriteMovies()
            .map { list -> list.map { mapper.mapToDomain(it) } }
    }

    override suspend fun getFavouriteMovieById(movieId: Int): MovieItem? {
        return moviesDao.getFavouriteMovieById(movieId)?.let { mapper.mapToDomain(it) }
    }

    override suspend fun addMovieToFavourites(movieItem: MovieItem) {
        moviesDao.insertMovie(mapper.mapToEntity(movieItem))
    }

    override suspend fun removeMovieFromFavourites(movieId: Int) {
        moviesDao.removeMovie(movieId)
    }
}