package com.example.kinopoiskpopularmovies.presentation.movie_details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.kinopoiskpopularmovies.R
import com.example.kinopoiskpopularmovies.databinding.FragmentMovieDetailsBinding
import com.example.kinopoiskpopularmovies.domain.MovieItem
import com.example.kinopoiskpopularmovies.domain.TrailerItem
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), OnTrailerClickListener {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var movie: MovieItem
    private lateinit var trailersAdapter: TrailersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: MovieDetailsFragmentArgs by navArgs()
        movie = args.movie
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupTrailersRecycler()
        setupObservers()
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData() {
        viewModel.loadTrailers(movie.kinopoiskId)
        viewModel.checkFavoriteStatus(movie.kinopoiskId)
    }

    private fun setupTrailersRecycler() {
        trailersAdapter = TrailersAdapter(this)
        binding.recyclerViewTrailers.apply {
            adapter = trailersAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun setupObservers() {
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            updateStarIcon(isFavorite ?: false)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
                viewModel.resetError()
            }
        }

        viewModel.trailers.observe(viewLifecycleOwner) { trailers ->
            trailersAdapter.submitList(trailers)
            binding.textViewTrailers.visibility = if (trailers.isNotEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.trailersError.observe(viewLifecycleOwner) { error ->
            error?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
                viewModel.resetError()
            }
        }
    }

    override fun onTrailerClick(trailerItem: TrailerItem) {
        val url = trailerItem.url
        if (url.isNotBlank()) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Snackbar.make(requireView(), "Не удалось открыть ссылку", Snackbar.LENGTH_SHORT).show()
            }
        } else {
            Snackbar.make(requireView(), "Ссылка недоступна", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupViews() {
        with(binding) {
            Glide.with(requireContext())
                .load(movie.posterUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewPoster)

            val rating = movie.kinopoiskRating
            textViewRating.visibility = if (rating == 0.0) View.GONE else View.VISIBLE

            if (rating > 0) {
                val backgroundDrawableId = when {
                    rating > 6 -> R.drawable.circle_green
                    rating > 4 -> R.drawable.circle_orange
                    else -> R.drawable.circle_red
                }
                textViewRating.background = ContextCompat.getDrawable(requireContext(), backgroundDrawableId)
                textViewRating.text = "%.1f".format(rating)
            }

            textViewTitle.text = movie.name
            textViewYear.text = if (movie.year > 0) movie.year.toString() else "—"
            textViewDescription.text = movie.description

            imageViewFavourite.setOnClickListener {
                viewModel.toggleFavorite(movie)
            }
        }
    }

    private fun updateStarIcon(isFavourite: Boolean) {
        val icon = if (isFavourite) R.drawable.ic_heart_fill else R.drawable.ic_heart
        binding.imageViewFavourite.setImageResource(icon)
    }
}