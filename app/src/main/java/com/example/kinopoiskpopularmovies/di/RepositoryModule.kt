package com.example.kinopoiskpopularmovies.di

import com.example.kinopoiskpopularmovies.data.repository.MoviesRepositoryImpl
import com.example.kinopoiskpopularmovies.data.local.FavouriteMoviesDao
import com.example.kinopoiskpopularmovies.data.mappers.MovieMapper
import com.example.kinopoiskpopularmovies.data.mappers.TrailerMapper
import com.example.kinopoiskpopularmovies.data.remote.MovieApi
import com.example.kinopoiskpopularmovies.domain.repository.MoviesRepository
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
    fun provideTrailerMapper(): TrailerMapper = TrailerMapper()

    @Provides
    @Singleton
    fun provideMoviesRepository(
        api: MovieApi,
        dao: FavouriteMoviesDao,
        movieMapper: MovieMapper,
        trailerMapper: TrailerMapper,
    ): MoviesRepository {
        return MoviesRepositoryImpl(api, dao, movieMapper, trailerMapper)
    }
}