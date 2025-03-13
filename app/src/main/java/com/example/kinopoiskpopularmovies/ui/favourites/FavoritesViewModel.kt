package com.example.kinopoiskpopularmovies.ui.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kinopoiskpopularmovies.data.MoviesRepositoryImpl
import com.example.kinopoiskpopularmovies.domain.MovieItem

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MoviesRepositoryImpl(application)
    val favorites: LiveData<List<MovieItem>> =
        repository.getFavoritesMovies()
}