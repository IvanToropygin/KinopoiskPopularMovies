package com.example.kinopoiskpopularmovies.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.databinding.FragmentMovieDetailsBinding
import com.example.kinopoiskpopularmovies.domain.MovieItem
import kotlinx.coroutines.launch

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var movie: MovieItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: MovieDetailsFragmentArgs by navArgs()
        movie = args.movie
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
//        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews() {
        Glide.with(requireContext())
            .load(movie.posterUrl)
            .into(binding.imageViewPoster)

        binding.textViewTitle.text = movie.name

        if (movie.year != 0) {
            binding.textViewYear.text = movie.year.toString()
        } else {
            binding.textViewYear.visibility = View.GONE
        }

        binding.imageViewStar.setOnClickListener {
            viewModel.toggleFavorite(movie)
        }
    }

//    private fun setupObservers() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.getFavouriteState(movie.kinopoiskId).collect { isFavourite ->
//                    updateStarIcon(isFavourite)
//                }
//            }
//        }
//    }

    private fun updateStarIcon(isFavourite: Boolean) {
        val icon = if (isFavourite) {
            android.R.drawable.star_big_on
        } else {
            android.R.drawable.star_big_off
        }
        binding.imageViewStar.setImageResource(icon)
    }


    companion object {
        const val ARG_MOVIE = "MOVIE_KEY"
    }
}