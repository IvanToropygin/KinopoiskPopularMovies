package com.example.kinopoiskpopularmovies.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kinopoiskpopularmovies.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class FavouriteMoviesDataBase : RoomDatabase() {

    abstract fun FavouritesMoviesDao(): FavouriteMoviesDao

    companion object {
        private const val DB_NAME = "favourite_movies.db"
        private var INSTANCE: FavouriteMoviesDataBase? = null
        private val LOCK = Any()

        fun getInstance(application: Application): FavouriteMoviesDataBase {
            INSTANCE?.let { return it }
            synchronized(LOCK) { INSTANCE?.let { return it } }
            val db = Room.databaseBuilder(
                application,
                FavouriteMoviesDataBase::class.java,
                DB_NAME
            ).build()
            INSTANCE = db
            return db
        }
    }
}