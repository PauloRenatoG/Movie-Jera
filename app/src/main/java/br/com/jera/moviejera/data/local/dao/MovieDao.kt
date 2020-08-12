package br.com.jera.moviejera.data.local.dao

import androidx.room.*
import br.com.jera.moviejera.data.entities.ApiMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: ApiMovie)

    @Query("SELECT * FROM apimovie")
    fun getWatchList(): Flow<List<ApiMovie>>

    @Delete
    suspend fun remove(movie: ApiMovie)
}