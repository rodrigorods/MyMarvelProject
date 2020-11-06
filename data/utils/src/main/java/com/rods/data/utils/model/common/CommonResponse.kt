package com.rods.data.utils.model.common

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

data class Item(
    val name: String,
    val resourceURI: String,
    val type: String? = null
)

data class Thumbnail(
    val extension: String,
    val path: String
)

data class MarvelUrl(
    val type: String,
    val url: String
)