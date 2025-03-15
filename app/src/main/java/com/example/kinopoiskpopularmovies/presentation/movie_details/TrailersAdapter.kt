package com.example.kinopoiskpopularmovies.presentation.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoiskpopularmovies.databinding.TrailerItemBinding
import com.example.kinopoiskpopularmovies.domain.models.TrailerItem

class TrailersAdapter(
    private val onTrailerClickListener: OnTrailerClickListener
) : ListAdapter<TrailerItem, TrailersViewHolder>(TrailerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {
        return TrailersViewHolder(
            TrailerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {
        val trailer = getItem(position)
        holder.binding.apply {
            textViewTrailerName.text = trailer.name
            root.setOnClickListener { onTrailerClickListener.onTrailerClick(trailer) }
        }
    }
}

class TrailerDiffCallback : DiffUtil.ItemCallback<TrailerItem>() {
    override fun areItemsTheSame(oldItem: TrailerItem, newItem: TrailerItem): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: TrailerItem, newItem: TrailerItem): Boolean {
        return oldItem == newItem
    }
}

class TrailersViewHolder(val binding: TrailerItemBinding) : RecyclerView.ViewHolder(binding.root)

interface OnTrailerClickListener {
    fun onTrailerClick(trailerItem: TrailerItem)
}