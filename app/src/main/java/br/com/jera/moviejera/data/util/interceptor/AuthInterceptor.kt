package br.com.jera.moviejera.data.util.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)

        return chain.proceed(chain.request().newBuilder().url(request.build()).build())
    }

    companion object {
        private const val API_KEY = "cc9a25e9341a8ea1a6d3b6da0ddb63a2"
    }
}