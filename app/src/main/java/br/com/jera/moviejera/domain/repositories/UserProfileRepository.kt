package br.com.jera.moviejera.domain.repositories

import br.com.jera.moviejera.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    suspend fun save(user: User)

    suspend fun getUser(email: String?): Flow<User>
}