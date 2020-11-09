package com.rods.data.character.api.datasource

import com.rods.data.character.api.model.CharactersResponse

interface CharacterDataSource {
    suspend fun getCharacters(): List<CharactersResponse>
}

class CharacterDataSourceFactory {
}