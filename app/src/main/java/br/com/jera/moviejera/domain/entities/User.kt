package br.com.jera.moviejera.domain.entities

data class User(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val dateOfBirth: String? = null,
    val photoUrl: String? = null
)