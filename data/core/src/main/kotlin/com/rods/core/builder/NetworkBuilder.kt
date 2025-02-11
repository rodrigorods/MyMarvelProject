package com.rods.core.builder

import com.rods.core.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(apiUrl: String, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(apiUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(buildLoggingInterceptor())
        .build()
}

private fun buildLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}