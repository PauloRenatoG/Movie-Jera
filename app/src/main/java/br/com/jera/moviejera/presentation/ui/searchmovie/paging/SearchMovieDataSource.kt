package br.com.jera.moviejera.presentation.ui.searchmovie.paging

import androidx.paging.PageKeyedDataSource
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.domain.usecases.SearchMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SearchMovieDataSource(
    private val searchMovie: SearchMovie,
    private val textSearch: String?,
    private val coroutineScope: CoroutineScope
) : PageKeyedDataSource<Int, Movie>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        coroutineScope.launch {
            runCatching {
                searchMovie.execute(textSearch, 1)
            }.onSuccess { response ->
                response.movies?.let { movie ->
                    callback.onResult(
                        movie,
                        null,
                        response.page?.plus(1)
                    )
                }
            }.onFailure {
                it
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            runCatching {
                searchMovie.execute(textSearch, params.key)
            }.onSuccess { response ->
                response.movies?.let { movie ->
                    callback.onResult(
                        movie,
                        params.key.plus(1)
                    )
                }
            }.onFailure {

            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            runCatching {
                searchMovie.execute(textSearch, params.key)
            }.onSuccess { response ->
                response.movies?.let { movie ->
                    callback.onResult(
                        movie,
                        params.key.minus(1)
                    )
                }
            }.onFailure {

            }
        }
    }
}