package br.com.jera.moviejera.data.repositories

import br.com.jera.moviejera.data.entities.ApiUser
import br.com.jera.moviejera.data.local.dao.UserDao
import br.com.jera.moviejera.data.util.Mapper
import br.com.jera.moviejera.domain.entities.User
import br.com.jera.moviejera.domain.repositories.SaveUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveUserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val mapperUser: Mapper<User, ApiUser>
) : SaveUserRepository {
    override suspend fun save(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insert(user.let(mapperUser::transform))
        }
    }
}