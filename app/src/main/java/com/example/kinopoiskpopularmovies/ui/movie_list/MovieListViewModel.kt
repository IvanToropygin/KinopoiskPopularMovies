package com.example.kinopoiskpopularmovies.ui.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoiskpopularmovies.models.Movie
import kotlinx.coroutines.flow.Flow

class MovieListViewModel : ViewModel() {

    val pagedMovies: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = { MoviePagingSource() }
    ).flow.cachedIn(viewModelScope)
}