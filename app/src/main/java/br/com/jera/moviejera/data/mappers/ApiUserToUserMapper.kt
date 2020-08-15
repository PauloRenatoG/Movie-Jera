package br.com.jera.moviejera.data.mappers

import br.com.jera.moviejera.data.entities.ApiUser
import br.com.jera.moviejera.data.util.Mapper
import br.com.jera.moviejera.domain.entities.User
import javax.inject.Inject

class ApiUserToUserMapper @Inject constructor(

) : Mapper<ApiUser, User>() {
    override fun transform(t: ApiUser) = User(
        id = t.id,
        name = t.name,
        email = t.email,
        photoUrl = t.photoUrl,
        dateOfBirth = t.dateOfBirth
    )
}