package com.rods.core.interceptor

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import okhttp3.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthInterceptorTest {

    private val authenticatedRequest = slot<Request>()
    private val interceptedChain = mockk<Interceptor.Chain>()

    @Before
    fun setup() {
        val currentRequest = Request.Builder()
            .url("http://com.rods/api")
            .build()

        every { interceptedChain.request() } returns currentRequest
        every { interceptedChain.proceed(capture(authenticatedRequest)) } returns mockk()
    }

    @Test
    fun intercept_addAuthenticationHeaders() {
        val authInterceptor = AuthInterceptor("timestamp", "publicApi", "privateApi")
        authInterceptor.intercept(interceptedChain)

        assertEquals(
            "http://com.rods/api?apikey=publicApi&ts=timestamp&hash=a1f56d0980f64a1e208eabe374227dee",
            authenticatedRequest.captured.url.toString()
        )
    }

}