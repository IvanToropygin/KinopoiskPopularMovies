package com.example.kinopoiskpopularmovies.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.kinopoiskpopularmovies.data.database.FavouriteMoviesDao
import com.example.kinopoiskpopularmovies.data.database.FavouriteMoviesDataBase
import com.example.kinopoiskpopularmovies.data.mappers.MovieMapper
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.domain.MoviesRepository
import com.example.kinopoiskpopularmovies.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(application: Application) : MoviesRepository {

    private val api = RetrofitClient.instance

    private val moviesDao: FavouriteMoviesDao =
        FavouriteMoviesDataBase.getInstance(application).FavouritesMoviesDao()

    private val mapper = MovieMapper()

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