package com.example.kinopoiskpopularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kinopoiskpopularmovies.domain.models.MovieItem
import com.example.kinopoiskpopularmovies.domain.repository.MoviesRepository
import com.example.kinopoiskpopularmovies.presentation.movie_details.MovieDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val mockRepository = mock<MoviesRepository>()
    private lateinit var viewModel: MovieDetailsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieDetailsViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `checkFavoriteStatus should set true when movie is favorite`() = runTest {
        val testMovie = MovieItem(
            kinopoiskId = 2,
            name = "Film 2",
            description = "Description2",
            rating = 7.0,
            year = 2020,
            posterUrl = "url2",
            isFavorite = true
        )
        whenever(mockRepository.getFavouriteMovieById(1)).thenReturn(testMovie)

        viewModel.checkFavoriteStatus(1)
        testDispatcher.scheduler.advanceUntilIdle() // Ждем завершения корутины

        assertEquals(true, viewModel.isFavorite.value)
    }

    @Test
    fun `checkFavoriteStatus should set false when movie is not favorite`() = runTest {
        whenever(mockRepository.getFavouriteMovieById(1)).thenReturn(null)

        viewModel.checkFavoriteStatus(1)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, viewModel.isFavorite.value)
    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule : TestWatcher() {
    private val testDispatcher = StandardTestDispatcher()

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}