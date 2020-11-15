package com.rods.data.utils.builder

import retrofit2.Retrofit

inline fun <reified T> createApi(retrofit: Retrofit): T = retrofit.create(T::class.java)