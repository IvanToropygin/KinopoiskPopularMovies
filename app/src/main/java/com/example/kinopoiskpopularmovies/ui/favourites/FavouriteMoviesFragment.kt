package com.example.kinopoiskpopularmovies.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kinopoiskpopularmovies.databinding.FragmentFavouriteMoviesBinding
import com.example.kinopoiskpopularmovies.ui.home.MoviesListFragmentDirections

class FavouriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavouriteMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var favouriteMoviesAdapter: FavouriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavouriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        favouriteMoviesAdapter = FavouriteMoviesAdapter { movie ->
            findNavController()
                .navigate(
                    MoviesListFragmentDirections
                        .actionNavigationHomeToMovieDetailsFragment(movie)
                )

            binding.recyclerViewFavouriteMovies.adapter = favouriteMoviesAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.favorites.observe(viewLifecycleOwner) { movies ->
            favouriteMoviesAdapter.submitList(movies)
        }
    }
}
