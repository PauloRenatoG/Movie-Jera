package br.com.jera.moviejera.data.repositories

import br.com.jera.moviejera.data.entities.ApiUser
import br.com.jera.moviejera.data.local.dao.UserDao
import br.com.jera.moviejera.data.util.Mapper
import br.com.jera.moviejera.domain.entities.User
import br.com.jera.moviejera.domain.repositories.UserProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val mapperUser: Mapper<User, ApiUser>,
    private val mapperApiUser: Mapper<ApiUser, User>
) : UserProfileRepository {
    override suspend fun save(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insert(user.let(mapperUser::transform))
        }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getUser(email: String?): Flow<User> {
        return userDao.getUser(email).mapLatest {
            mapperApiUser.transform(it)
        }.flowOn(Dispatchers.IO)
            .conflate()
    }
}