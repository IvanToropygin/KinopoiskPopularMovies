package com.example.kinopoiskpopularmovies

import androidx.paging.PagingSource
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.domain.MoviesRepository
import com.example.kinopoiskpopularmovies.presentation.home.MoviePagingSource
import com.example.kinopoiskpopularmovies.presentation.home.PopularMoviesViewModel
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class PopularMoviesViewModelTest {

    private val mockRepository = mock(MoviesRepository::class.java)
    private lateinit var viewModel: PopularMoviesViewModel

    @Before
    fun setup() {
        viewModel = PopularMoviesViewModel(mockRepository)
    }

    @Test
    fun `viewModel initializes correctly`() {
        assertNotNull(viewModel.pagedMovies)
    }

    @Test
    fun `paging source loads data`() = runTest {
        val testMovies = listOf(
            MovieItem(
                kinopoiskId = 1,
                name = "Name1",
                description = "Description1",
                rating = 5.5,
                year = 2025,
                posterUrl = "url1",
                isFavorite = false
            ),
            MovieItem(
                kinopoiskId = 2,
                name = "Name2",
                description = "Description2",
                rating = 7.0,
                year = 2020,
                posterUrl = "url2",
                isFavorite = true)
        )

        `when`(mockRepository.getPopularMovies(1)).thenReturn(testMovies)

        val pagingSource = MoviePagingSource(mockRepository)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )
        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(2, (result as PagingSource.LoadResult.Page).data.size)
    }
}