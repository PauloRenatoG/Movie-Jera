package br.com.jera.moviejera.domain.repositories

import br.com.jera.moviejera.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface WatchListRepository {

    suspend fun insert(movie: Movie)

    suspend fun getWatchList(userId: Int?): Flow<List<Movie>?>

    suspend fun remove(movie: Movie)
}