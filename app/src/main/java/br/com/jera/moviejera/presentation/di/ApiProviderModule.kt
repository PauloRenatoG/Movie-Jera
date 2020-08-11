package br.com.jera.moviejera.presentation.di

import br.com.jera.moviejera.BuildConfig
import br.com.jera.moviejera.BuildConfig.API_ENDPOINT
import br.com.jera.moviejera.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ApiProviderModule {

    @Singleton
    @Provides
    @Named(API_ENDPOINT)
    fun providesApiEndPoint(): String {
        return BuildConfig.API_ENDPOINT
    }

    @Provides
    @IntoSet
    fun provideHttpLoggingInterceptor(logLevel: HttpLoggingInterceptor.Level): Interceptor {
        return HttpLoggingInterceptor().setLevel(logLevel)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        for (interceptor in interceptors) {
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideLogLevel(): HttpLoggingInterceptor.Level {
        return HttpLoggingInterceptor.Level.HEADERS
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        @Named(API_ENDPOINT) apiEndPoint: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiEndPoint)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}