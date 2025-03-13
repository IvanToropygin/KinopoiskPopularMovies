package com.example.kinopoiskpopularmovies.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.domain.MoviesRepository

class FavoritesViewModel(
    private val repository: MoviesRepository,
) : ViewModel() {
    val favorites: LiveData<List<MovieItem>> =
        repository.getFavoritesMovies()
}