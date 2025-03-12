package com.example.kinopoiskpopularmovies.ui.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MovieListViewModel : ViewModel() {

    val pagedMovies = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = { MoviePagingSource() }
    ).flow.cachedIn(viewModelScope)
}