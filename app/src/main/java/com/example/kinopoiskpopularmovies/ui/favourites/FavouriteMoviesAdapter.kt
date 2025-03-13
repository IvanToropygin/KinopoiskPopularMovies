package com.example.kinopoiskpopularmovies.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.databinding.MovieItemBinding
import com.example.kinopoiskpopularmovies.domain.MovieItem

class FavouriteMoviesAdapter(
    private val onFavouriteMovieClickListener: OnFavouriteMovieClickListener,
) : ListAdapter<MovieItem, FavouriteMovieViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMovieViewHolder {
        return FavouriteMovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteMovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            with(holder.binding) {
                Glide.with(imageViewPoster.context)
                    .load(movie.posterUrl)
                    .into(imageViewPoster)

                root.setOnClickListener { onFavouriteMovieClickListener.onMovieClick(movie) }
            }
        }
    }
}

class FavouriteMovieViewHolder(val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<MovieItem>() {

    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem) =
        oldItem.kinopoiskId == newItem.kinopoiskId

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem) = oldItem == newItem
}

fun interface OnFavouriteMovieClickListener {
    fun onMovieClick(movie: MovieItem)
}