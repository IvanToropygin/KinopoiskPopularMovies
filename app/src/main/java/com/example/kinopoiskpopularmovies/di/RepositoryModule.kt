package com.example.kinopoiskpopularmovies.di

import com.example.kinopoiskpopularmovies.data.MoviesRepositoryImpl
import com.example.kinopoiskpopularmovies.data.database.FavouriteMoviesDao
import com.example.kinopoiskpopularmovies.data.mappers.MovieMapper
import com.example.kinopoiskpopularmovies.data.network.MovieApi
import com.example.kinopoiskpopularmovies.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieMapper(): MovieMapper = MovieMapper()

    @Provides
    @Singleton
    fun provideMoviesRepository(
        api: MovieApi,
        dao: FavouriteMoviesDao,
        mapper: MovieMapper,
    ): MoviesRepository {
        return MoviesRepositoryImpl(api, dao, mapper)
    }
}