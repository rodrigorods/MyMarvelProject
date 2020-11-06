package com.rods.core.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        //TODO INJECT HASH
        val url = req.url().newBuilder().addQueryParameter("APPID", "your_key_here").build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)

//        val requestBuilder = chain.request().newBuilder()
//        networkHeaders.forEach {
//            requestBuilder.addHeader(it.type, it.value)
//        }
//        val request = requestBuilder.build()
//        return chain.proceed(request)
    }
}