package com.rods.data.test

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestHelper {



}

inline fun <reified T> createTestApi(buildUrl: HttpUrl): T = Retrofit.Builder()
    .baseUrl(buildUrl)
    .client(OkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(T::class.java)

fun <T : Any> readTestResourceFile(javaClass: Class<T>, fileName: String): String {
    val fileInputStream = javaClass.classLoader?.getResourceAsStream(fileName)
    val reader = fileInputStream?.bufferedReader()
    val resourceText = reader?.readText() ?: ""
    reader?.close()

    return resourceText
}