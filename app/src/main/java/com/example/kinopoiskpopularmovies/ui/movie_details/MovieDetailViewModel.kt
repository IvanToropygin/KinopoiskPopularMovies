package com.example.kinopoiskpopularmovies.ui.movie_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskpopularmovies.data.MoviesRepositoryImpl
import com.example.kinopoiskpopularmovies.domain.MovieItem
import kotlinx.coroutines.launch

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MoviesRepositoryImpl(application)

    private val _movie = MutableLiveData<MovieItem>()
    val movie: LiveData<MovieItem> = _movie

    private val _isFavorite = MutableLiveData<Boolean>(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun loadMovieFavouriteState(movieId: Int) {
        viewModelScope.launch {
            try {
                _isFavorite.value = repository.getFavouriteMovieById(movieId) != null
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            _isFavorite.value?.let { currentState ->
                try {
                    if (currentState) {
                        repository.removeMovieFromFavourites(movieId)
                    } else {
                        // Получаем актуальные данные фильма при добавлении
                        repository.getFavouriteMovieById(movieId)
                            ?.let { repository.addMovieToFavourites(it) }
                    }
                    _isFavorite.value = !currentState
                } catch (e: Exception) {
                    // Handle error
                }
            }
        }
    }
}