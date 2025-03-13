package com.example.kinopoiskpopularmovies.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.domain.MoviesRepository
import kotlinx.coroutines.delay

class MoviePagingSource(
    private val repository: MoviesRepository,
) : PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val page = params.key ?: FIRST_PAGE
            val movies = repository.getPopularMovies(page)

            delay(1000)

            LoadResult.Page(
                data = movies,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}