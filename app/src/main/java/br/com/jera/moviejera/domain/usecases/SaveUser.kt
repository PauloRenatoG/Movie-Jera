package br.com.jera.moviejera.domain.usecases

import br.com.jera.moviejera.domain.entities.User
import br.com.jera.moviejera.domain.repositories.SaveUserRepository
import javax.inject.Inject

class SaveUser @Inject constructor(
    private val repository: SaveUserRepository
) {
    suspend fun execute(user: User) = repository.save(user)
}