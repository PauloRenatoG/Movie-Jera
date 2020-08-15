package br.com.jera.moviejera.presentation.di

import br.com.jera.moviejera.data.entities.ApiMovie
import br.com.jera.moviejera.data.entities.ApiSearchResponse
import br.com.jera.moviejera.data.entities.ApiUser
import br.com.jera.moviejera.data.mappers.*
import br.com.jera.moviejera.data.util.Mapper
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.domain.entities.SearchResponse
import br.com.jera.moviejera.domain.entities.User
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface MapperModule {

    @Binds
    fun bindsApiMovieToMovieMapper(
        mapper: ApiMovieToMovieMapper
    ): Mapper<ApiMovie, Movie>

    @Binds
    fun bindsApiSearchResponseToSearchResponseMapper(
        mapper: ApiSearchResponseToSearchResponseMapper
    ): Mapper<ApiSearchResponse, SearchResponse>

    @Binds
    fun bindsMovieToApiMovieMapper(
        mapper: MovieToApiMovieMapper
    ): Mapper<Movie, ApiMovie>

    @Binds
    fun bindsUserToApiUserMapper(
        mapper: UserToApiUserMapper
    ): Mapper<User, ApiUser>

    @Binds
    fun bindsApiUserToUserMapper(
        mapper: ApiUserToUserMapper
    ): Mapper<ApiUser, User>
}