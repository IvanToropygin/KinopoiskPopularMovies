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
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: MutableLiveData<String?> = _errorMessage

    fun checkFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            try {
                _isFavorite.value = repository.getFavouriteMovieById(movieId) != null
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка при проверке статуса"
                _errorMessage.value = null
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
                _errorMessage.value = "Не удалось обновить"
                _errorMessage.value = null
            }
        }
    }
}