package br.com.jera.moviejera.data.remote

import br.com.jera.moviejera.data.entities.ApiSearchResponse
import br.com.jera.moviejera.data.entities.TokenTemporary
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("authentication/guest_session/new")
    suspend fun getToken(): TokenTemporary

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") textSearch: String,
        @Query("page") page: Int
    ): ApiSearchResponse
}
