package com.example.kinopoiskpopularmovies.presentation.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.domain.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {

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