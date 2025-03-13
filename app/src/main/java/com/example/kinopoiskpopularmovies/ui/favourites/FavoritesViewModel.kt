package com.example.kinopoiskpopularmovies.ui.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kinopoiskpopularmovies.data.MoviesRepositoryImpl
import com.example.kinopoiskpopularmovies.domain.MovieItem

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MoviesRepositoryImpl(application)

    private val _favorites = MutableLiveData<List<MovieItem>>()
    val favorites: LiveData<List<MovieItem>> = _favorites

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        repository.getFavoritesMovies().observeForever {
            _favorites.value = it
        }
    }
}