package com.example.kinopoiskpopularmovies.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.domain.MoviesRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _movie = MutableLiveData<MovieItem>()
    val movie: LiveData<MovieItem> = _movie

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun loadMovieFavouriteState(movieId: Int) {
        viewModelScope.launch {
            try {
                repository.getFavouriteMovieById(movieId)?.let {
                    _isFavorite.value = true
                } ?: run {
                    _isFavorite.value = false
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun toggleFavorite(movie: MovieItem) {
        viewModelScope.launch {
            if (movie.isFavorite) {
                repository.removeMovieFromFavourites(movie.kinopoiskId)
            } else {
                repository.addMovieToFavourites(movie)
            }
            _isFavorite.value = !movie.isFavorite
        }
    }
}