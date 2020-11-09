package com.rods.data.character.api.datasource

import com.rods.data.character.api.model.CharactersResponse
import com.rods.data.utils.model.Data

interface CharacterDataSource {
    suspend fun getCharacters(batchSize: Int, offset: Int): Data<CharactersResponse>
}

class CharacterDataSourceFactory {
}