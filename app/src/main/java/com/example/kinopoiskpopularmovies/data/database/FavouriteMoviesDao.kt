package com.example.kinopoiskpopularmovies.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kinopoiskpopularmovies.data.database.models.MovieEntity

@Dao
interface FavouriteMoviesDao {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM favourite_movies WHERE id = :movieId")
    suspend fun getFavouriteMovieById(movieId: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("DELETE FROM favourite_movies WHERE id = :movieId")
    suspend fun removeMovie(movieId: Int)
}