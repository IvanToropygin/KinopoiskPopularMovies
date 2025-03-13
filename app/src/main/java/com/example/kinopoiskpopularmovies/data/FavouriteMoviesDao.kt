package com.example.kinopoiskpopularmovies.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kinopoiskpopularmovies.models.Movie

@Dao
interface FavouriteMoviesDao {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM favourite_movies WHERE id = :movieId")
    fun getFavouriteMovieById(movieId: Int): LiveData<Movie>

    @Insert
    suspend fun insertMovie(movie: Movie)

    @Query("DELETE FROM favourite_movies WHERE id = :movieId")
    suspend fun removeMovie(movieId: Int)
}