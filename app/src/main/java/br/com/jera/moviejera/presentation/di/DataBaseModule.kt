package br.com.jera.moviejera.presentation.di

import android.content.Context
import br.com.jera.moviejera.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = AppDataBase.build(context)

    @Provides
    @Singleton
    fun provideUserDao(dataBase: AppDataBase) = dataBase.userDao()

    @Provides
    @Singleton
    fun provideMovieDao(dataBase: AppDataBase) = dataBase.movieDao()
}