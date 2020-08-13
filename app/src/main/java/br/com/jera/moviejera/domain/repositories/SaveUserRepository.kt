package br.com.jera.moviejera.domain.repositories

import br.com.jera.moviejera.domain.entities.User

interface SaveUserRepository {
    suspend fun save(user: User)
}