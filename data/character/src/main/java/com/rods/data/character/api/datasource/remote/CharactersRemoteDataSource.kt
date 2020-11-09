package com.rods.data.character.api.datasource.remote

import com.rods.data.character.api.CharacterApi
import com.rods.data.character.api.datasource.CharacterDataSource

class CharactersRemoteDataSource(
    private val api: CharacterApi
): CharacterDataSource {
    override suspend fun getCharacters(
        batchSize: Int,
        offset: Int
    ) = api.getCharacters(batchSize, offset).data
}