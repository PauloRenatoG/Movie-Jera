package br.com.jera.moviejera.presentation.ui.watchlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.jera.moviejera.domain.entities.Movie

class WatchListAdapter(
    private val callbackClick: (Movie) -> Unit
) : ListAdapter<Movie, WatchListViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        return WatchListViewHolder.inflate(parent, callbackClick)
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}