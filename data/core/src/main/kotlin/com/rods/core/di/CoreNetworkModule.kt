package com.rods.core.di

import com.rods.core.builder.provideOkHttpClient
import com.rods.core.builder.provideRetrofit
import com.rods.core.interceptor.AuthInterceptor
import com.rods.core.model.NetworkConfiguration
import org.koin.dsl.module

fun buildCoreNetworkModule(apiUrl: String, publicKey: String, privateKey: String) = module {
    single { NetworkConfiguration(apiUrl, publicKey, privateKey) }
    factory {
        val apiConfig = get<NetworkConfiguration>()
        AuthInterceptor(
            timestamp = System.currentTimeMillis().toString(),
            publicKey = apiConfig.publicKey,
            privateKey = apiConfig.privateKey
        )
    }
    factory { provideOkHttpClient(get()) }
    single {
        provideRetrofit(
            apiUrl = get<NetworkConfiguration>().apiUrl,
            okHttpClient = get()
        )
    }
}