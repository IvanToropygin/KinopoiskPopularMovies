package com.example.kinopoiskpopularmovies.presentation.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.databinding.FragmentMovieDetailsBinding
import com.example.kinopoiskpopularmovies.domain.MovieItem

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
        setupObservers()
        viewModel.checkFavoriteStatus(movie.kinopoiskId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            updateStarIcon(isFavorite ?: false)
        }
    }

    private fun setupViews() {
        with(binding) {
            Glide.with(requireContext())
                .load(movie.posterUrl)
                .into(imageViewPoster)

            textViewTitle.text = movie.name

            if (movie.year != 0) {
                textViewYear.text = movie.year.toString()
            } else {
                textViewYear.visibility = View.GONE
            }

            textViewDescription.text = movie.description

            imageViewStar.setOnClickListener {
                viewModel.toggleFavorite(movie)
            }
        }
    }

    private fun updateStarIcon(isFavourite: Boolean) {
        val icon = if (isFavourite) {
            android.R.drawable.star_big_on
        } else {
            android.R.drawable.star_big_off
        }
        binding.imageViewStar.setImageResource(icon)
    }
}