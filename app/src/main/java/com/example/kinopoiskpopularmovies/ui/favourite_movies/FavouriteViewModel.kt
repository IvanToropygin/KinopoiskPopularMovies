package com.example.kinopoiskpopularmovies.ui.favourite_movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kinopoiskpopularmovies.data.FavouriteMoviesDao
import com.example.kinopoiskpopularmovies.data.FavouriteMoviesDataBase
import com.example.kinopoiskpopularmovies.models.Movie

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val favouriteMoviesDao: FavouriteMoviesDao =
        FavouriteMoviesDataBase.getInstance(application).FavouritesMoviesDao()

    fun getFavouriteMovies(): LiveData<List<Movie>> = favouriteMoviesDao.getAllFavouriteMovies()

}