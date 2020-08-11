package br.com.jera.moviejera.presentation.di

import br.com.jera.moviejera.data.repositories.SearchMovieRepositoryImpl
import br.com.jera.moviejera.data.util.interceptor.AuthInterceptor
import br.com.jera.moviejera.domain.repositories.SearchMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor

@InstallIn(ApplicationComponent::class)
@Module
interface ApplicationBindModule {

    @Binds
    @IntoSet
    fun bindsAuthInterceptor(interceptor: AuthInterceptor): Interceptor

    @Binds
    fun bindsSearchMovieRepository(repository: SearchMovieRepositoryImpl): SearchMovieRepository
}