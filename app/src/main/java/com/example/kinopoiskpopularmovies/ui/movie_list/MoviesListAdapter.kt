package com.example.kinopoiskpopularmovies.ui.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoiskpopularmovies.databinding.MovieItemBinding
import com.example.kinopoiskpopularmovies.models.Movie

class MoviesListAdapter(
    private val onLoadMore: () -> Unit
) : RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()
    private var isLoading = false

    fun setLoading(loading: Boolean) {
        isLoading = loading
    }

    fun updateList(newItems: List<Movie>) {
        val startPosition = movies.size
        movies.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        if (position >= movies.size - 5 && !isLoading) {
            isLoading = true
            onLoadMore()
        }
    }

    override fun getItemCount() = movies.size

    inner class MovieViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(root)
                    .load(movie.posterUrl)
                    .into(imageViewPoster)
            }
        }
    }
}