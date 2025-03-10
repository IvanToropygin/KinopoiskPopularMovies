package com.example.kinopoiskpopularmovies.network

import com.example.kinopoiskpopularmovies.models.MovieResponse
import com.example.kinopoiskpopularmovies.models.TrailerResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("films/collections?type=TOP_POPULAR_MOVIES")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): MovieResponse

    @GET("films/{kinopoiskId}/videos")
    suspend fun loadTrailers(
        @Path("kinopoiskId") kinopoiskId: Long,
    ): TrailerResponseDTO

    @GET("films/{kinopoiskId}/reviews?order=DATE_DESC")
    suspend fun loadReviews(
        @Path("kinopoiskId") kinopoiskId: Long,
    ): TrailerResponseDTO

    companion object {
        const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/"
    }
}