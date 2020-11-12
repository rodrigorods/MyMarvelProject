package com.rods.data.character.api.datasource

import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter

interface CharacterRemoteDataSource {
    suspend fun getCharacters(batchSize: Int, offset: Int): CharactersPage
}

interface CharacterLocalDataSource {
    suspend fun getFavoriteCharacters(): CharactersPage
    suspend fun addCharacterToFavorite(character: MarvelCharacter)
    suspend fun removeFromFavorite(character: MarvelCharacter)
}
