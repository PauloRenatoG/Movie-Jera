package br.com.jera.moviejera.presentation.ui.watchlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.domain.usecases.WatchList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WatchListViewModel @ViewModelInject constructor(
    private val watchList: WatchList
) : ViewModel(), LifecycleObserver {

    private lateinit var _watchListMovie: Flow<List<Movie>?>
    val watchListMovie: Flow<List<Movie>?> get() = _watchListMovie

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        viewModelScope.launch {
            _watchListMovie = watchList.getWatchList()
        }
    }

    internal fun remove(movie: Movie) {
        viewModelScope.launch {
            watchList.remove(movie)
        }
    }
}