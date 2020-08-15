package br.com.jera.moviejera.data.entities

import com.google.gson.annotations.SerializedName

data class ApiSearchResponse(
    val page: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("results")
    val movies: List<ApiMovie>?
)