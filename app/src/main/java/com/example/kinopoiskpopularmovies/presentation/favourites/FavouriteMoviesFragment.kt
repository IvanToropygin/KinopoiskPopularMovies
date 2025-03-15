package com.example.kinopoiskpopularmovies.presentation.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.kinopoiskpopularmovies.databinding.FragmentFavouriteMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        setupSwipeToDelete()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        favouriteMoviesAdapter = FavouriteMoviesAdapter { movie ->
            findNavController()
                .navigate(
                    FavouriteMoviesFragmentDirections
                        .actionNavigationFavouritesToMovieDetailsFragment(movie)
                )
        }

        binding.recyclerViewFavouriteMovies.adapter = favouriteMoviesAdapter
    }

    private fun observeViewModel() {
        viewModel.favorites.observe(viewLifecycleOwner) { movies ->
            favouriteMoviesAdapter.submitList(movies)

            TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
            if (movies.isNullOrEmpty()) {
                binding.emptyStateText.visibility = View.VISIBLE
                binding.recyclerViewFavouriteMovies.visibility = View.GONE
            } else {
                binding.emptyStateText.visibility = View.GONE
                binding.recyclerViewFavouriteMovies.visibility = View.VISIBLE
            }
        }
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val movie = favouriteMoviesAdapter.currentList[position]
                viewModel.removeMovieFromFavourites(movie.kinopoiskId)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerViewFavouriteMovies)
    }
}
