package br.com.jera.moviejera.domain.entities

data class SearchResponse(
    val page: Int? = null,
    val totalResults: Int? = null,
    val totalPages: Int? = null,
    val movies: List<Movie>? = null
)