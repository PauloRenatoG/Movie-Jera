package br.com.jera.moviejera.presentation.ui.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.jera.moviejera.R
import br.com.jera.moviejera.databinding.ItemSearchBinding
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.presentation.ui.util.loadImage

class WatchListViewHolder(
    private val binding: ItemSearchBinding,
    private val callbackClick: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(movie: Movie) {
        with(binding) {
            titleMovie.text = movie.title
            imagePoster.loadImage(movie.posterPath)
            favoriteButton.setImageResource(R.drawable.ic_favorite)
        }
        setClickListener(movie)
    }

    private fun setClickListener(movie: Movie) {
        with(binding) {
            favoriteButton.setOnClickListener {
                movie.favorite = !movie.favorite
                callbackClick.invoke(movie)
            }
        }
    }

    companion object {
        fun inflate(parent: ViewGroup, callbackClick: (Movie) -> Unit): WatchListViewHolder {
            return WatchListViewHolder(
                ItemSearchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                callbackClick
            )
        }
    }
}