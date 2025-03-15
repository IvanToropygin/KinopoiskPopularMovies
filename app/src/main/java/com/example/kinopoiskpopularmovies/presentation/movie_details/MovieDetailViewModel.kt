package com.example.kinopoiskpopularmovies.presentation.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.domain.MoviesRepository
import com.example.kinopoiskpopularmovies.domain.TrailerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {

    private val _trailers = MutableLiveData<List<TrailerItem>>()
    val trailers: LiveData<List<TrailerItem>> = _trailers

    private val _trailersError = MutableLiveData<String?>()
    val trailersError: LiveData<String?> = _trailersError

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun checkFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            try {
                _isFavorite.value = repository.getFavouriteMovieById(movieId) != null
            } catch (e: Exception) {
                handleError("Ошибка при проверке статуса")
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
                handleError("Не удалось обновить")
            }
        }
    }

    fun loadTrailers(movieId: Int) {
        viewModelScope.launch {
            try {
                val trailers = repository.getTrailers(movieId)
                    .filter { it.url.isNotBlank() }
                    .takeIf { it.isNotEmpty() }
                    ?: throw Exception("Нет доступных трейлеров")

                _trailers.postValue(trailers)
            } catch (e: Exception) {
                _trailersError.postValue("Ошибка загрузки трейлеров: ${e.message}")
            }
        }
    }

    fun resetError() {
        _errorMessage.value = null
        _trailersError.value = null
    }

    private fun handleError(message: String) {
        _errorMessage.value = message
        resetError()
    }
}
