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

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun checkFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            try {
                _isFavorite.value = repository.getFavouriteMovieById(movieId) != null
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }

    fun toggleFavorite(movie: MovieItem) {
        viewModelScope.launch {
            try {
                val current = _isFavorite.value ?: false
                if (current) {
                    repository.removeMovieFromFavourites(movie.kinopoiskId)
                } else {
                    repository.addMovieToFavourites(movie)
                }
                _isFavorite.value = !current
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }
}