package com.example.kinopoiskpopularmovies.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.databinding.FragmentMovieDetailsBinding
import com.example.kinopoiskpopularmovies.models.Movie

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: Movie = arguments?.get(MOVIE_KEY) as Movie

        with(binding) {

            Glide.with(this@MovieDetailsFragment)
                .load(movie.posterUrl)
                .into(imageViewPoster)

            textViewTitle.text = movie.name

            if (movie.year != 0) textViewYear.text =
                movie.year.toString() else textViewYear.visibility = View.GONE

            textViewDescription.text = movie.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MOVIE_KEY = "MOVIE_KEY"
    }
}