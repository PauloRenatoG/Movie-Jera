package br.com.jera.moviejera.data.mappers

import br.com.jera.moviejera.data.entities.ApiUser
import br.com.jera.moviejera.data.util.Mapper
import br.com.jera.moviejera.domain.entities.User
import javax.inject.Inject

class UserToApiUserMapper @Inject constructor(

) : Mapper<User, ApiUser>() {
    override fun transform(t: User) = ApiUser(
        id = t.id,
        name = t.name,
        email = t.email,
        photoUrl = t.photoUrl,
        dateOfBirth = t.dateOfBirth
    )
}