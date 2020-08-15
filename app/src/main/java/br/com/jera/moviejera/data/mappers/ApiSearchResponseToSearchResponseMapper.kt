package br.com.jera.moviejera.data.mappers

import br.com.jera.moviejera.data.entities.ApiMovie
import br.com.jera.moviejera.data.entities.ApiSearchResponse
import br.com.jera.moviejera.data.util.Mapper
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.domain.entities.SearchResponse
import javax.inject.Inject

class ApiSearchResponseToSearchResponseMapper @Inject constructor(
    private val mapper: Mapper<ApiMovie, Movie>
): Mapper<ApiSearchResponse, SearchResponse>() {
    override fun transform(t: ApiSearchResponse) = SearchResponse(
        page = t.page,
        totalPages = t.totalPages,
        totalResults = t.totalResults,
        movies = t.movies.let(mapper::transformList)
    )
}