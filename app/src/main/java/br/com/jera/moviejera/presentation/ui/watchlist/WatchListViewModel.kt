package br.com.jera.moviejera.presentation.ui.watchlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.domain.usecases.UserProfile
import br.com.jera.moviejera.domain.usecases.WatchList
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WatchListViewModel @ViewModelInject constructor(
    private val watchList: WatchList,
    private val userProfile: UserProfile
) : ViewModel(), LifecycleObserver {

    private var _watchListMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val watchListMovie: LiveData<List<Movie>> get() = _watchListMovie

    @InternalCoroutinesApi
    internal fun getUser(email: String?) {
        viewModelScope.launch {
            val user = userProfile.getUser(email).first()
            _watchListMovie.value = watchList.getWatchList(user.id).first()
        }
    }

    internal fun remove(movie: Movie) {
        viewModelScope.launch {
            watchList.remove(movie)
        }
    }
}