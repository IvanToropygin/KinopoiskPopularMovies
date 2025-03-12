package com.example.kinopoiskpopularmovies.ui.movie_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.kinopoiskpopularmovies.databinding.FragmentMovieListBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MoviesListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel by viewModels()
    private var moviesListAdapter = MoviesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewMovies.adapter = moviesListAdapter

        viewModel.pagedMovies.onEach {
            moviesListAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        moviesListAdapter.addLoadStateListener { loadState ->
            Log.d("Paging", "Load state: $loadState")
            val isLoading = loadState.refresh is LoadState.Loading
            Log.d("Paging", "Is loading: $isLoading")
            binding.progressBarLoading.isVisible = isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}