package com.example.kinopoiskpopularmovies.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouriteMoviesDao {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies(): LiveData<List<MovieItemDBModel>>

    @Query("SELECT * FROM favourite_movies WHERE id = :movieId")
    fun getFavouriteMovieById(movieId: Int): LiveData<MovieItemDBModel>

    @Insert
    suspend fun insertMovie(movie: MovieItemDBModel)

    @Query("DELETE FROM favourite_movies WHERE id = :movieId")
    suspend fun removeMovie(movieId: Int)
}