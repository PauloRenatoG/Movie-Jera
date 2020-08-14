package br.com.jera.moviejera.data.local.dao

import androidx.room.*
import br.com.jera.moviejera.data.entities.ApiUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: ApiUser)

    @Query("SELECT * FROM user WHERE user.email == :email")
    fun getUser(email: String?): Flow<ApiUser>

    @Update
    fun update(user: ApiUser?)
}