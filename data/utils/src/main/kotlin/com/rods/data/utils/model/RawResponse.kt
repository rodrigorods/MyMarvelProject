package com.rods.data.utils.model

data class RawResponse<out T>(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data<T>,
    val etag: String,
    val status: String
)

data class Data<out E>(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<E>,
    val total: Int
)
