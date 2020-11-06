package com.rods.data.character.api.datasource

interface CharacterDataSource {
    suspend fun getCharacters()
}

class CharacterDataSourceFactory {
}