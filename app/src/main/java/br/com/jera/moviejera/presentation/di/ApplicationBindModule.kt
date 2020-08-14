package br.com.jera.moviejera.presentation.di

import br.com.jera.moviejera.data.repositories.SearchMovieRepositoryImpl
import br.com.jera.moviejera.data.repositories.UserProfileRepositoryImpl
import br.com.jera.moviejera.data.repositories.WatchListRepositoryImpl
import br.com.jera.moviejera.domain.repositories.SearchMovieRepository
import br.com.jera.moviejera.domain.repositories.UserProfileRepository
import br.com.jera.moviejera.domain.repositories.WatchListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface ApplicationBindModule {

    @Binds
    fun bindsSearchMovieRepository(repository: SearchMovieRepositoryImpl): SearchMovieRepository

    @Binds
    fun bindsWatchListRepository(repository: WatchListRepositoryImpl): WatchListRepository

    @Binds
    fun bindsUserProfileRepository(repository: UserProfileRepositoryImpl): UserProfileRepository
}