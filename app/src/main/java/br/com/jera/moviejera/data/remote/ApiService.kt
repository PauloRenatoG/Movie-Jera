package br.com.jera.moviejera.data.remote

import br.com.jera.moviejera.data.entities.ApiSearchResponse
import br.com.jera.moviejera.data.entities.TokenTemporary
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("authentication/guest_session/new")
    suspend fun getToken(): Response<TokenTemporary>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") text: String,
        @Query("page") page: Int
    ): Response<ApiSearchResponse>
}
