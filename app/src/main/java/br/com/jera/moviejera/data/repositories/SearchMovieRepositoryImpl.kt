package br.com.jera.moviejera.data.repositories

import br.com.jera.moviejera.data.entities.ApiSearchResponse
import br.com.jera.moviejera.data.remote.ApiService
import br.com.jera.moviejera.data.util.Mapper
import br.com.jera.moviejera.domain.entities.SearchResponse
import br.com.jera.moviejera.domain.repositories.SearchMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchMovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper<ApiSearchResponse, SearchResponse>
) : SearchMovieRepository {
    override suspend fun searchMovie(textSearch: String?, page: Int): SearchResponse {
        return withContext(Dispatchers.IO) {
            apiService.searchMovie(textSearch = textSearch, page = page).let(mapper::transform)
        }
    }
}