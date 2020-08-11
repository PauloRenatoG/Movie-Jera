package br.com.jera.moviejera.presentation.ui.searchmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.jera.moviejera.databinding.ItemSearchBinding
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.presentation.ui.util.loadImage

class SearchMovieViewHolder(
    private val binding: ItemSearchBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(movie: Movie) {
        with(binding) {
            titleMovie.text = movie.title
            imagePoster.loadImage(movie.posterPath)
        }
    }

    companion object {
        fun inflate(parent: ViewGroup): SearchMovieViewHolder {
            return SearchMovieViewHolder(
                ItemSearchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}