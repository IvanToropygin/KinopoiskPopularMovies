package com.example.kinopoiskpopularmovies.network

import com.example.kinopoiskpopularmovies.models.MovieResponseDTO
import com.example.kinopoiskpopularmovies.models.TrailerResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("films/collections?type=TOP_POPULAR_MOVIES")
    suspend fun loadMovies(
        @Query("page") page: Int,
    ): MovieResponseDTO

    @GET("films/{kinopoiskId}/videos")
    suspend fun loadTrailers(
        @Path("kinopoiskId") kinopoiskId: Long,
    ): TrailerResponseDTO

    @GET("films/{kinopoiskId}/reviews?order=DATE_DESC")
    suspend fun loadReviews(
        @Path("kinopoiskId") kinopoiskId: Long,
    ): TrailerResponseDTO
}