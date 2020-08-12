package br.com.jera.moviejera.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.jera.moviejera.data.entities.ApiUser

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: ApiUser)
}