package com.example.kinopoiskpopularmovies.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kinopoiskpopularmovies.R
import com.example.kinopoiskpopularmovies.databinding.FragmentFavouriteMoviesBinding
import com.example.kinopoiskpopularmovies.models.Movie
import com.example.kinopoiskpopularmovies.ui.movie_details.MovieDetailsFragment

class FavouriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavouriteMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouriteViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    private lateinit var favouriteMoviesAdapter: FavouriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavouriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favouriteMoviesAdapter = FavouriteMoviesAdapter { movie -> onFavoriteMovieClick(movie) }

        binding.recyclerViewFavouriteMovies.adapter = favouriteMoviesAdapter

//        favouriteMoviesAdapter.submitList(viewModel.getFavouriteMovies().value)
        favouriteMoviesAdapter.submitList(listOf(Movie(
            kinopoiskId = 5405057,
            name = "Анора",
            description = "Бруклин. Стриптизерша Анора, предпочитающая имя...",
            countries = emptyList(),
            rating = 1.1,
            year = 2024,
            posterUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp/5405057.jpg"
        )))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onFavoriteMovieClick(movie: Movie) {
        val bundle = bundleOf(MovieDetailsFragment.MOVIE_KEY to movie)

        findNavController().navigate(
            R.id.action_moviesListFragment_to_movieDetailsFragment,
            bundle)
    }
}