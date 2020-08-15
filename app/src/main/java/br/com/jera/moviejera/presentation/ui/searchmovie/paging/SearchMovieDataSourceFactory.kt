package br.com.jera.moviejera.presentation.ui.searchmovie.paging

import androidx.paging.DataSource
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.domain.usecases.SearchMovie
import kotlinx.coroutines.CoroutineScope

class SearchMovieDataSourceFactory constructor(
    private val searchMovie: SearchMovie,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Int, Movie>() {
    var textSearch: String = INIT_SEARCH

    override fun create(): DataSource<Int, Movie> {
        return SearchMovieDataSource(searchMovie, textSearch, coroutineScope)
    }

    companion object {
        private const val INIT_SEARCH = "A"
    }
}