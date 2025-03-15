package com.example.kinopoiskpopularmovies.presentation.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.R
import com.example.kinopoiskpopularmovies.databinding.FragmentMovieDetailsBinding
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupViews() {
        with(binding) {
            Glide.with(requireContext())
                .load(movie.posterUrl)
                .into(imageViewPoster)


            val rating = movie.kinopoiskRating

            if (rating == 0.0) {
                textViewRating.visibility = View.GONE
            } else {
                val backgroundDrawableId =
                when {
                    (rating > 6) -> R.drawable. circle_green
                    (rating > 4) -> R.drawable. circle_orange
                    else -> R.drawable. circle_red
                }

                val background = ContextCompat.getDrawable(requireContext(), backgroundDrawableId)
                textViewRating.background = background;
                textViewRating.text = rating.toString()
            }


            textViewRating.text

            textViewTitle.text = movie.name

            if (movie.year != 0) {
                textViewYear.text = movie.year.toString()
            } else {
                textViewYear.visibility = View.GONE
            }

            textViewDescription.text = movie.description

            imageViewFavourite.setOnClickListener {
                viewModel.toggleFavorite(movie)
            }
        }
    }

    private fun updateStarIcon(isFavourite: Boolean) {
        val icon = if (isFavourite) {
            R.drawable.ic_heart_fill
        } else {
            R.drawable.ic_heart
        }
        binding.imageViewFavourite.setImageResource(icon)
    }
}