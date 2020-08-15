package br.com.jera.moviejera.domain.repositories

import br.com.jera.moviejera.domain.entities.SearchResponse

interface SearchMovieRepository {
    suspend fun searchMovie(textSearch: String?, page: Int): SearchResponse
}