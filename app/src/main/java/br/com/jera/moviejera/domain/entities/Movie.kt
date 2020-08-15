package br.com.jera.moviejera.domain.entities

data class Movie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val id: Int? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    var favorite: Boolean = false,
    var userId: Int? = null
)