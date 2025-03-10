package com.example.kinopoiskpopularmovies.ui.movie_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskpopularmovies.models.Movie
import com.example.kinopoiskpopularmovies.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val repository = MovieRepository()
    private var currentPage = 1
    private var totalPages = 1
    private var isLoading = false

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies: LiveData<List<Movie>> get() = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoading

    fun loadMovies() {
        if (isLoading || currentPage > totalPages) return

        isLoading = true
        _isLoading.postValue(true)

        viewModelScope.launch {
            try {
                val response = repository.getMovies(currentPage)

                response.let { movieResponse ->
                        totalPages = movieResponse.totalPages
                        val newList = _movies.value!! + movieResponse.movies
                        val uniqueMovies = newList.distinctBy { it.kinopoiskId }

                        _movies.postValue(uniqueMovies)
                        currentPage++
                    }

            } catch (e: Exception) {
                Log.e("ViewModel", "Network error: ${e.message}")
            } finally {
                isLoading = false
                _isLoading.postValue(false)
            }
        }
    }
}