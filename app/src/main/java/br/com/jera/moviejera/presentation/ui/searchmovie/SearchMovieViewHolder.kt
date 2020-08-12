package br.com.jera.moviejera.presentation.ui.searchmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.jera.moviejera.R
import br.com.jera.moviejera.databinding.ItemSearchBinding
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.presentation.ui.util.loadImage

class SearchMovieViewHolder(
    private val binding: ItemSearchBinding,
    private val callbackClick: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(movie: Movie) {
        with(binding) {
            titleMovie.text = movie.title
            imagePoster.loadImage(movie.posterPath)
        }
        setClickListener(movie)
    }

    private fun setClickListener(movie: Movie) {
        with(binding) {
            favoriteButton.setOnClickListener {
                if (movie.favorite) {
                    favoriteButton.setImageResource(R.drawable.ic_baseline_favorite)
                } else {
                    favoriteButton.setImageResource(R.drawable.ic_favorite)
                }
                movie.favorite = !movie.favorite
                callbackClick.invoke(movie)
            }
        }

    }

    companion object {
        fun inflate(parent: ViewGroup, callbackClick: (Movie) -> Unit): SearchMovieViewHolder {
            return SearchMovieViewHolder(
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