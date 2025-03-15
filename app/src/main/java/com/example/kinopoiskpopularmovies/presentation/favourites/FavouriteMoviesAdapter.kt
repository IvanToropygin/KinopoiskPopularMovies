package com.example.kinopoiskpopularmovies.presentation.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.R
import com.example.kinopoiskpopularmovies.databinding.MovieItemFavouriteListBinding
import com.example.kinopoiskpopularmovies.domain.models.MovieItem

class FavouriteMoviesAdapter(
    private val onFavouriteMovieClickListener: OnFavouriteMovieClickListener,
) : ListAdapter<MovieItem, FavouriteMovieViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMovieViewHolder {
        return FavouriteMovieViewHolder(
            MovieItemFavouriteListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteMovieViewHolder, position: Int) {
        val movie = getItem(position) ?: return
        with(holder.binding) {
            Glide.with(imageViewPoster.context)
                .load(movie.posterUrl)
                .into(imageViewPoster)

            textViewTitle.text = movie.name
            textViewOriginalTitle.handleOriginalTitle(movie.nameOriginal)

            textViewRating.handleRating(R.string.rating_label, movie.kinopoiskRating)
            textViewRatingImdb.handleRating(R.string.ratingIMdb_label, movie.imdbRating)

            textViewYear.handleYear(movie.year)

            root.setOnClickListener { onFavouriteMovieClickListener.onMovieClick(movie) }
        }
    }

    private fun TextView.handleOriginalTitle(title: String?) {
        visibility = if (title.isNullOrEmpty() || title == "null") {
            View.GONE
        } else {
            text = context.getString(R.string.original_name_label, title)
            View.VISIBLE
        }
    }

    private fun TextView.handleRating(@StringRes labelRes: Int, rating: Double) {
        text = if (rating > 0.0) {
            context.getString(labelRes, rating.formatRating())
        } else {
            context.getString(labelRes, context.getString(R.string.data_is_null_message))
        }
    }

    private fun TextView.handleYear(year: Int) {
        text = if (year > 0) {
            context.getString(R.string.year_label, year.toString())
        } else {
            context.getString(R.string.year_label, context.getString(R.string.data_is_null_message))
        }
    }

    private fun Double.formatRating() = "%.1f".format(this)
}

class FavouriteMovieViewHolder(val binding: MovieItemFavouriteListBinding) :
    RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<MovieItem>() {

    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem) =
        oldItem.kinopoiskId == newItem.kinopoiskId

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem) = oldItem == newItem
}

fun interface OnFavouriteMovieClickListener {
    fun onMovieClick(movie: MovieItem)
}