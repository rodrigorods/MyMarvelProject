package com.rods.core.builder

import com.rods.core.interceptor.AuthInterceptor
import io.mockk.mockk
import okhttp3.OkHttpClient
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.converter.gson.GsonConverterFactory

class NetworkBuilderKtTest {

    private val client = mockk<OkHttpClient>()
    private val authInterceptor = mockk<AuthInterceptor>()

    @Test
    fun provideRetrofit_createDefaultRetrofit() {
        val retrofit = provideRetrofit("http://com.rods/", client)
        assertEquals(retrofit.baseUrl().toString(), "http://com.rods/")
        assertTrue(retrofit.callFactory() is OkHttpClient)
        assertNotNull(retrofit.converterFactories().find { it is GsonConverterFactory })
    }

    @Test
    fun provideOkHttpClient_createOkHttpClient() {
        val client = provideOkHttpClient(authInterceptor)
        assertNotNull(client.interceptors.find { it is AuthInterceptor })
    }
}