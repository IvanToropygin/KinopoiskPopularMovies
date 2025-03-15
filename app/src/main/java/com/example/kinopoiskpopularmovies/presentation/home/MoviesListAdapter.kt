package com.example.kinopoiskpopularmovies.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.databinding.MovieItemPopularListBinding
import com.example.kinopoiskpopularmovies.domain.models.MovieItem

class MoviesListAdapter(
    private val onMovieClickListener: OnMovieClickListener,
) : PagingDataAdapter<MovieItem, PopularMoviesViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
            MovieItemPopularListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { movie ->
            with(holder.binding) {
                Glide.with(imageViewPoster.context)
                    .load(movie.posterUrl)
                    .into(imageViewPoster)

                root.setOnClickListener { onMovieClickListener.onMovieClick(item) }
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<MovieItem>() {

    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem) =
        oldItem.kinopoiskId == newItem.kinopoiskId

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem) = oldItem == newItem
}

fun interface OnMovieClickListener {
    fun onMovieClick(movie: MovieItem)
}