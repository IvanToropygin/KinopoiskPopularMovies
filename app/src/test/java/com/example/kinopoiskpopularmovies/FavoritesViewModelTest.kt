package com.example.kinopoiskpopularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.kinopoiskpopularmovies.domain.models.MovieItem
import com.example.kinopoiskpopularmovies.domain.repository.MoviesRepository
import com.example.kinopoiskpopularmovies.presentation.favourites.FavoritesViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class FavoritesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository = mock<MoviesRepository>()
    private lateinit var viewModel: FavoritesViewModel

    @Before
    fun setup() {
        `when`(mockRepository.getFavoritesMovies())
            .thenReturn(MutableLiveData(emptyList()))

        viewModel = FavoritesViewModel(mockRepository)
    }

    @Test
    fun `should load favorites`() {

        val testData = listOf(
            MovieItem(
                kinopoiskId = 1,
                name = "Film 1",
                description = "Description1",
                year = 2025,
                posterUrl = "url1",
                isFavorite = false,
                nameOriginal = "anora",
                kinopoiskRating = 5.5,
                imdbRating = 6.0
            ),
            MovieItem(
                kinopoiskId = 2,
                name = "Film 2",
                description = "Description2",
                year = 2020,
                posterUrl = "url2",
                isFavorite = true,
                nameOriginal = "original",
                kinopoiskRating = 4.4,
                imdbRating = 5.0
            )
        )

        val testLiveData = MutableLiveData<List<MovieItem>>()
        testLiveData.value = testData

        `when`(mockRepository.getFavoritesMovies())
            .thenReturn(testLiveData)

        viewModel = FavoritesViewModel(mockRepository)

        assertEquals(2, viewModel.favorites.value?.size)
    }

    @Test
    fun `should handle null in repository`() {

        `when`(mockRepository.getFavoritesMovies())
            .thenReturn(MutableLiveData())

        viewModel = FavoritesViewModel(mockRepository)

        assertNull(viewModel.favorites.value)
    }
}