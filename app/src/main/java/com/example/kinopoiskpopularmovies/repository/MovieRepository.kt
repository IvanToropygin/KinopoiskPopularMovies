package com.example.kinopoiskpopularmovies.repository

import com.example.kinopoiskpopularmovies.models.Movie
import com.example.kinopoiskpopularmovies.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository {

    private val api = RetrofitClient.instance
    suspend fun getMovies(page: Int): List<Movie> {
        return withContext(Dispatchers.IO) {
            api.getPopularMovies(page).movies
        }
    }
}