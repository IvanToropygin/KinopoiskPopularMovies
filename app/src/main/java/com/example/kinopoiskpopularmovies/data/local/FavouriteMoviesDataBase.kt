package com.example.kinopoiskpopularmovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kinopoiskpopularmovies.data.local.models.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class FavouriteMoviesDataBase : RoomDatabase() {

    abstract fun FavouritesMoviesDao(): FavouriteMoviesDao
}