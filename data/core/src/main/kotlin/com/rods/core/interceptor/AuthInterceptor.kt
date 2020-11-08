package com.rods.core.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.PublicKey

private const val HASH_KEY = "hash"
private const val TIMESTAMP_KEY = "ts"
private const val PUBLICKEY_KEY = "apikey"

class AuthInterceptor(
    private val timestamp: String,
    private val publicKey: String,
    private val privateKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val md5Hash = (timestamp + privateKey + publicKey).toMD5Hash()

        val request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter(PUBLICKEY_KEY, publicKey)
            .addQueryParameter(TIMESTAMP_KEY, timestamp)
            .addQueryParameter(HASH_KEY, md5Hash)
            .build()

        return chain.proceed(request.newBuilder().url(url).build())
    }

    private fun String.toMD5Hash(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}