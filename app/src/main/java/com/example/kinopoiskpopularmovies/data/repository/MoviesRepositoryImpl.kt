package com.example.kinopoiskpopularmovies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.kinopoiskpopularmovies.data.local.FavouriteMoviesDao
import com.example.kinopoiskpopularmovies.data.mappers.MovieMapper
import com.example.kinopoiskpopularmovies.data.mappers.TrailerMapper
import com.example.kinopoiskpopularmovies.data.remote.MovieApi
import com.example.kinopoiskpopularmovies.domain.models.MovieItem
import com.example.kinopoiskpopularmovies.domain.repository.MoviesRepository
import com.example.kinopoiskpopularmovies.domain.models.TrailerItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val moviesDao: FavouriteMoviesDao,
    private val movieMapper: MovieMapper,
    private val trailerMapper: TrailerMapper
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): List<MovieItem> {
        return withContext(Dispatchers.IO) {
            api.getPopularMovies(page).movies
        }.map { movieMapper.mapToDomain(it) }
    }

    override suspend fun getTrailers(kinopoiskId: Int): List<TrailerItem> {
        return withContext(Dispatchers.IO) {
            api.getTrailers(kinopoiskId).trailers
        }.map { trailerMapper.mapToDomain(it) }
    }

    override fun getFavoritesMovies(): LiveData<List<MovieItem>> {
        return moviesDao.getAllFavouriteMovies()
            .map { list -> list.map { movieMapper.mapToDomain(it) } }
    }

    override suspend fun getFavouriteMovieById(movieId: Int): MovieItem? {
        return moviesDao.getFavouriteMovieById(movieId)?.let { movieMapper.mapToDomain(it) }
    }

    override suspend fun addMovieToFavourites(movieItem: MovieItem) {
        moviesDao.insertMovie(movieMapper.mapToEntity(movieItem))
    }

    override suspend fun removeMovieFromFavourites(movieId: Int) {
        moviesDao.removeMovie(movieId)
    }
}