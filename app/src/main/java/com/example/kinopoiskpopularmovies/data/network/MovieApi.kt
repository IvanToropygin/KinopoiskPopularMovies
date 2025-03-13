package com.example.kinopoiskpopularmovies.data.network

import com.example.kinopoiskpopularmovies.data.network.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("films/collections?type=TOP_POPULAR_MOVIES")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): MovieResponse

    companion object {
        const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/"
    }
}