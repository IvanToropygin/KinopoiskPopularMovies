package com.example.kinopoiskpopularmovies.presentation.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.R
import com.example.kinopoiskpopularmovies.databinding.MovieItemFavouriteListBinding
import com.example.kinopoiskpopularmovies.domain.MovieItem

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
        val movie = getItem(position)
        if (movie != null) {
            with(holder.binding) {
                Glide.with(imageViewPoster.context)
                    .load(movie.posterUrl)
                    .into(imageViewPoster)

                textViewTitle.text = movie.name
                if (movie.nameOriginal == "null") textViewOriginalTitle.visibility = View.GONE
                else textViewOriginalTitle.text = root.context.getString(R.string.original_name_label, movie.nameOriginal)

                if (movie.kinopoiskRating == 0.0) textViewRating.visibility = View.GONE
                else textViewRating.text = root.context.getString(R.string.rating_label, movie.kinopoiskRating.toString())

                if (movie.imdbRating == 0.0) textViewRatingImdb.visibility = View.GONE
                else textViewRatingImdb.text = root.context.getString(R.string.ratingIMdb_label, movie.imdbRating.toString())

                if (movie.year == 0) textViewYear.visibility = View.GONE
                else textViewYear.text = root.context.getString(R.string.year_label, movie.year)

                root.setOnClickListener { onFavouriteMovieClickListener.onMovieClick(movie) }
            }
        }
    }
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