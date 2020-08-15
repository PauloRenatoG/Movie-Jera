package br.com.jera.moviejera.data.remote

import br.com.jera.moviejera.data.entities.ApiSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") key: String? = API_KEY,
        @Query("query") textSearch: String?,
        @Query("page") page: Int
    ): ApiSearchResponse

    companion object {
        private const val API_KEY = "cc9a25e9341a8ea1a6d3b6da0ddb63a2"
    }
}
