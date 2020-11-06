package com.rods.data.character.api.model

import com.rods.data.utils.model.common.*

data class CharactersResponse(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Long,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<MarvelUrl>
)

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
