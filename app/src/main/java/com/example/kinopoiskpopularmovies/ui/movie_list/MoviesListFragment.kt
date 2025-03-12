package com.example.kinopoiskpopularmovies.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.kinopoiskpopularmovies.R
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

        with(binding.swipeRefresh) {
            setProgressViewOffset(true, 0, 100)
            setColorSchemeColors(ContextCompat.getColor(this.context, R.color.kinopoisk_orange))
            setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this.context, R.color.kinopoisk_dark))
        }

        binding.recyclerViewMovies.adapter = moviesListAdapter
            .withLoadStateFooter(LoadStateAdapter())

        binding.swipeRefresh.setOnRefreshListener { moviesListAdapter.refresh() }

        moviesListAdapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.pagedMovies.onEach {
            moviesListAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}