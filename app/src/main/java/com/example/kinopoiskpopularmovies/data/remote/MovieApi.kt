package com.example.kinopoiskpopularmovies.data.remote

import com.example.kinopoiskpopularmovies.data.remote.models.MovieResponse
import com.example.kinopoiskpopularmovies.data.remote.models.TrailersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("films/collections?type=TOP_POPULAR_MOVIES")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieResponse

    @GET("films/{kinopoiskId}/videos")
    suspend fun getTrailers(@Path("kinopoiskId") kinopoiskId: Int): TrailersResponse

    companion object {
        const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/"
    }
}