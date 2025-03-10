package com.example.kinopoiskpopularmovies.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kinopoiskpopularmovies.databinding.FragmentMovieListBinding

class MoviesListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var adapter: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        loadInitialData()
    }

    private fun setupRecyclerView() {
        adapter = MoviesListAdapter { viewModel.loadMovies() }
        binding.recyclerViewMovies.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            // Обновляем только если список не пустой
            if (movies.isNotEmpty()) {
                adapter.updateList(movies)
            }
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            with(binding) {
                progressBarLoading.isVisible = isLoading
                if (viewModel.movies.value?.isNotEmpty() == true) {
                    progressBarLoading.isVisible = false
                }
            }
            adapter.setLoading(isLoading)
        }
    }

    private fun loadInitialData() {
        if (viewModel.movies.value.isNullOrEmpty()) {
            viewModel.loadMovies()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}