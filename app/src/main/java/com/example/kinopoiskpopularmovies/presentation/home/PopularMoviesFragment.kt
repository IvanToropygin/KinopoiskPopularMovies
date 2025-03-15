package com.example.kinopoiskpopularmovies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.kinopoiskpopularmovies.R
import com.example.kinopoiskpopularmovies.databinding.FragmentPopularMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PopularMoviesViewModel by viewModels()
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRefreshLayout()
        setupAdapter()
        observePagingData()
    }

    private fun setupRefreshLayout() {
        with(binding.swipeRefresh) {
            setProgressViewOffset(true, 0, 100)
            setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.kinopoisk_orange))
            setProgressBackgroundColorSchemeColor(
                ContextCompat.getColor(requireContext(), R.color.kinopoisk_dark)
            )
            setOnRefreshListener { moviesListAdapter.refresh() }
        }
    }

    private fun setupAdapter() {
        moviesListAdapter = MoviesListAdapter { movie ->
            findNavController()
                .navigate(
                    PopularMoviesFragmentDirections
                        .actionNavigationHomeToMovieDetailsFragment(movie)
                )
        }

        binding.recyclerViewMovies.adapter = moviesListAdapter.withLoadStateFooter(
            footer = MyLoadStateAdapter()
        )

        moviesListAdapter.addLoadStateListener { loadState ->
            binding.swipeRefresh.isRefreshing = loadState.source.refresh is LoadState.Loading
        }
    }

    private fun observePagingData() {
        lifecycleScope.launch {
            viewModel.pagedMovies.collectLatest {
                moviesListAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}