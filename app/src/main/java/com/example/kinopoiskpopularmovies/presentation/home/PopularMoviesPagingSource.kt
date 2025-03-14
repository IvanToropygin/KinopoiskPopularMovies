package com.example.kinopoiskpopularmovies.presentation.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoiskpopularmovies.domain.models.MovieItem
import com.example.kinopoiskpopularmovies.domain.repository.MoviesRepository

class PopularMoviesPagingSource(
    private val repository: MoviesRepository,
) : PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val page = params.key ?: FIRST_PAGE
            val movies = repository.getPopularMovies(page)

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