package br.com.jera.moviejera.data.repositories

import br.com.jera.moviejera.data.local.dao.MovieDao
import br.com.jera.moviejera.data.mappers.ApiMovieToMovieMapper
import br.com.jera.moviejera.data.mappers.MovieToApiMovieMapper
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.domain.repositories.WatchListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WatchListRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val mapper: MovieToApiMovieMapper,
    private val mapperToMovie: ApiMovieToMovieMapper
) : WatchListRepository {
    override suspend fun insert(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.insert(movie.let(mapper::transform))
        }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getWatchList(userId: Int?): Flow<List<Movie>?> {
        return movieDao.getWatchList(userId).mapLatest {
            it.let(mapperToMovie::transformList)
        }.flowOn(Dispatchers.IO)
            .conflate()
    }

    override suspend fun remove(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.remove(movie.let(mapper::transform))
        }
    }
}
