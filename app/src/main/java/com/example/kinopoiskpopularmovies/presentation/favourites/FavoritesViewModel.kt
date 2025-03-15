package com.example.kinopoiskpopularmovies.presentation.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskpopularmovies.domain.models.MovieItem
import com.example.kinopoiskpopularmovies.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {

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

    fun removeMovieFromFavourites(movieId: Int) {
        viewModelScope.launch {
            repository.removeMovieFromFavourites(movieId)
        }
    }
}