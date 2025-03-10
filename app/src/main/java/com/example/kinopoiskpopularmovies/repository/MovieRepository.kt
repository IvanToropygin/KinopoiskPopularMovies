package com.example.kinopoiskpopularmovies.repository

import com.example.kinopoiskpopularmovies.network.RetrofitClient

class MovieRepository {

    private val api = RetrofitClient.instance
    suspend fun getMovies(page: Int) = api.getPopularMovies(page)
}