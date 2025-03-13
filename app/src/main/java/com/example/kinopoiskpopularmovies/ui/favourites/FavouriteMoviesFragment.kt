package com.example.kinopoiskpopularmovies.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kinopoiskpopularmovies.R
import com.example.kinopoiskpopularmovies.databinding.FragmentFavouriteMoviesBinding
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.ui.movie_details.MovieDetailsFragment

class FavouriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavouriteMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var favouriteMoviesAdapter: FavouriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeViewModel()
    }

    private fun setupAdapter() {
        favouriteMoviesAdapter = FavouriteMoviesAdapter { movie ->
            navigateToMovieDetails(movie)
        }

        binding.recyclerViewFavouriteMovies.adapter = favouriteMoviesAdapter
    }

    private fun observeViewModel() {
        viewModel.favorites.observe(viewLifecycleOwner) { movies ->
            favouriteMoviesAdapter.submitList(movies)
        }
    }

    private fun navigateToMovieDetails(movie: MovieItem) {
        val bundle = bundleOf(MovieDetailsFragment.ARG_MOVIE to movie)
        findNavController().navigate(
            R.id.action_navigation_favourites_to_movieDetailsFragment,
            bundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}