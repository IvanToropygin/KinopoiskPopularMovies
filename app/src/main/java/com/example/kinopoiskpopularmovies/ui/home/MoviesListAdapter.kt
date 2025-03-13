package com.example.kinopoiskpopularmovies.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.databinding.MovieItemBinding
import com.example.kinopoiskpopularmovies.models.Movie

class MoviesListAdapter(
    private val onMovieClickListener: OnMovieClickListener,
) : PagingDataAdapter<Movie, MovieViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            with(holder.binding) {
                Glide.with(imageViewPoster.context)
                    .load(item.posterUrl)
                    .into(imageViewPoster)

                root.setOnClickListener { onMovieClickListener.onMovieClick(item) }
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem.kinopoiskId == newItem.kinopoiskId

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
}

fun interface OnMovieClickListener { fun onMovieClick(movie: Movie) }