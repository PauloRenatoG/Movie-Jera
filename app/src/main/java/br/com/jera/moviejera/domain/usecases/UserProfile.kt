package br.com.jera.moviejera.domain.usecases

import br.com.jera.moviejera.domain.entities.User
import br.com.jera.moviejera.domain.repositories.UserProfileRepository
import javax.inject.Inject

class UserProfile @Inject constructor(
    private val repository: UserProfileRepository
) {
    suspend fun execute(user: User) = repository.save(user)

    suspend fun getUser(email: String?) = repository.getUser(email)
}