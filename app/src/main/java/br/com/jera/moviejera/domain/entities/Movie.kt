package br.com.jera.moviejera.domain.entities

open class Movie(
    open val adult: Boolean? = null,
    open val backdropPath: String? = null,
    open val id: Int? = null,
    open val originalLanguage: String? = null,
    open val originalTitle: String? = null,
    open val overview: String? = null,
    open val popularity: Double? = null,
    open val posterPath: String? = null,
    open val releaseDate: String? = null,
    open val title: String? = null,
    open val video: Boolean? = null,
    open val voteAverage: Double? = null,
    open val voteCount: Int? = null
)