package br.com.jera.moviejera.domain.usecases

import br.com.jera.moviejera.domain.repositories.SearchMovieRepository
import javax.inject.Inject

class SearchMovie @Inject constructor(
    private val repository: SearchMovieRepository
) {
    suspend fun execute(textSearch: String?, page: Int) = repository.searchMovie(textSearch, page)
}