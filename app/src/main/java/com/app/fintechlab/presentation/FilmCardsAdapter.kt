package com.app.fintechlab.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.fintechlab.R
import com.app.fintechlab.databinding.FilmCardBinding
import com.app.fintechlab.presentation.entities.FilmCard

class FilmCardsAdapter(
    private val onItemClicked: (FilmCard) -> Unit,
    private val onItemLongClicked: (FilmCard) -> Unit
) : ListAdapter<FilmCard, FilmCardsAdapter.FilmCardsViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmCardsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilmCardBinding.inflate(inflater, parent, false)
        return FilmCardsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmCardsViewHolder, position: Int) {
        val filmCard = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(filmCard)
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClicked(filmCard)
            holder.makeFavourite()
            return@setOnLongClickListener true
        }
        holder.bind(filmCard)
    }

    class FilmCardsViewHolder(
        private val binding: FilmCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filmCard: FilmCard) {
            binding.root.tag = filmCard
            binding.filmName.text = filmCard.name
            binding.filmGenre.text = filmCard.genreYear
            binding.filmPosterPreview.load(filmCard.posterUrlPreview.toUri().buildUpon().scheme("https").build())
            binding.favouriteImage.setImageResource(filmCard.favourite)
        }

        fun makeFavourite() {
            binding.favouriteImage.setImageResource(R.drawable.favourite_star)
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<FilmCard>() {
        override fun areItemsTheSame(oldItem: FilmCard, newItem: FilmCard): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: FilmCard, newItem: FilmCard): Boolean {
            return oldItem.name == newItem.name
        }
    }

}