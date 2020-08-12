package br.com.jera.moviejera.data.mappers

import br.com.jera.moviejera.data.entities.ApiMovie
import br.com.jera.moviejera.data.util.Mapper
import br.com.jera.moviejera.domain.entities.Movie
import javax.inject.Inject

class MovieToApiMovieMapper @Inject constructor(

) : Mapper<Movie, ApiMovie>() {
    override fun transform(t: Movie) = ApiMovie(
        id = t.id,
        adult = t.adult,
        backdropPath = t.backdropPath,
        originalLanguage = t.originalLanguage,
        originalTitle = t.originalTitle,
        overview = t.overview,
        popularity = t.popularity,
        posterPath = t.posterPath,
        releaseDate = t.releaseDate,
        title = t.title,
        video = t.video,
        voteAverage = t.voteAverage,
        voteCount = t.voteCount,
        favorite = t.favorite
    )
}