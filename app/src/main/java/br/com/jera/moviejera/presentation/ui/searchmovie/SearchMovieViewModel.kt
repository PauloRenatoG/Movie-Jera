package br.com.jera.moviejera.presentation.ui.searchmovie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.domain.usecases.SearchMovie
import br.com.jera.moviejera.domain.usecases.WatchList
import br.com.jera.moviejera.presentation.ui.searchmovie.paging.SearchMovieDataSourceFactory
import kotlinx.coroutines.launch

class SearchMovieViewModel @ViewModelInject constructor(
    private val searchMovie: SearchMovie,
    private val watchList: WatchList
) : ViewModel() {

    val listMovies: LiveData<PagedList<Movie>> get() = _listMovies
    private val _listMovies by lazy { dataSourceFactory.toLiveData(pagedListConfig) }

    private val dataSource = SearchMovieDataSourceFactory(
        searchMovie,
        viewModelScope
    )

    private val dataSourceFactory = object : DataSource.Factory<Int, Movie>() {
        override fun create(): DataSource<Int, Movie> {
            return dataSource.create()
        }
    }

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPrefetchDistance(10)
        .build()

    internal fun searchMovies(value: String) {
        dataSource.textSearch = value

        refreshSearch()
    }

    internal fun addFavorite(movie: Movie) {
        viewModelScope.launch {
            watchList.execute(movie)
        }
    }

    private fun refreshSearch() {
        _listMovies.value?.dataSource?.invalidate()
    }

    companion object {
        private const val PAGE_SIZE = 30
    }
}