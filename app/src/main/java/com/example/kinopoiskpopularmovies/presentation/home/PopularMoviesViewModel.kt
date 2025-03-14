package com.example.kinopoiskpopularmovies.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.kinopoiskpopularmovies.data.MoviesRepositoryImpl

class PopularMoviesViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MoviesRepositoryImpl(application)

    val pagedMovies = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
        pagingSourceFactory = { MoviePagingSource(repository) }
    ).flow.cachedIn(viewModelScope)
}