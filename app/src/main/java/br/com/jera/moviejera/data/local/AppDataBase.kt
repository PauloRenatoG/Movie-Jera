package br.com.jera.moviejera.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.jera.moviejera.data.entities.ApiMovie
import br.com.jera.moviejera.data.entities.ApiUser
import br.com.jera.moviejera.data.local.dao.MovieDao
import br.com.jera.moviejera.data.local.dao.UserDao

@Database(entities = [ApiUser::class, ApiMovie::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun movieDao(): MovieDao

    companion object {
        private const val DATABASE_NAME = "movie-jera-db"

        fun build(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java, DATABASE_NAME
            ).build()
        }
    }
}