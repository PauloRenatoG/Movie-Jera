package br.com.jera.moviejera.domain.usecases

import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.domain.repositories.WatchListRepository
import javax.inject.Inject

class WatchList @Inject constructor(
    private val repository: WatchListRepository
) {
    suspend fun execute(movie: Movie) = repository.insert(movie)

    suspend fun getWatchList() = repository.getWatchList()

    suspend fun remove(movie: Movie) = repository.remove(movie)
}