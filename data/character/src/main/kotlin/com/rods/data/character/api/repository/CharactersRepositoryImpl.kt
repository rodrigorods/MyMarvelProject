package com.rods.data.character.api.repository

import com.rods.data.character.api.datasource.CharacterDataSourceProvider
import com.rods.data.utils.repository.safeApiCall
import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.character.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val dataSource: CharacterDataSourceProvider
): CharactersRepository {

    override suspend fun getCharacters(batchSize: Int, offset: Int, searchTerm: String?) = safeApiCall {
        val favoritedList = dataSource.getLocal().getFavoriteCharacters()
        dataSource.getRemote().getCharacters(batchSize, offset, searchTerm).apply {
            characters.map {
                if (favoritedList.characters.contains(it)) it.favorited = true
            }
        }
    }

    override suspend fun getFavoriteCharacters() = safeApiCall {
        dataSource.getLocal().getFavoriteCharacters()
    }

    override suspend fun favorite(character: MarvelCharacter) = safeApiCall {
        dataSource.getLocal().addCharacterToFavorite(character)
    }

    override suspend fun unfavorite(character: MarvelCharacter) = safeApiCall {
        dataSource.getLocal().removeFromFavorite(character)
    }
}