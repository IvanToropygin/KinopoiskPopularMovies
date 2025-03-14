package com.example.kinopoiskpopularmovies.di

import android.content.Context
import androidx.room.Room
import com.example.kinopoiskpopularmovies.data.database.FavouriteMoviesDao
import com.example.kinopoiskpopularmovies.data.database.FavouriteMoviesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavouriteMoviesDataBase {
        return Room.databaseBuilder(
            context,
            FavouriteMoviesDataBase::class.java,
            "favourite_movies.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: FavouriteMoviesDataBase): FavouriteMoviesDao {
        return database.FavouritesMoviesDao()
    }
}