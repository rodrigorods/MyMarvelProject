package com.rods.data.character.api.datasource.remote

import com.rods.data.character.api.CharacterApi
import com.rods.data.character.api.model.CharactersResponse
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter

interface CharacterRemoteDataSource {
    suspend fun getCharacters(batchSize: Int, offset: Int, searchTerm: String?): CharactersPage
}

class CharacterRemoteDataSourceImpl(
    private val api: CharacterApi
): CharacterRemoteDataSource {
    override suspend fun getCharacters(
        batchSize: Int,
        offset: Int,
        searchTerm: String?
    ): CharactersPage {
        val response = api.getCharacters(batchSize, offset, searchTerm).data

        return CharactersPage (
            hasMorePages = response.total > response.offset,
            characters = response.results.map { it.toMarvelCharacter() }.toMutableList()
        )
    }

    private fun CharactersResponse.toMarvelCharacter() = MarvelCharacter(
        id = id,
        name = name,
        description = description,
        thumbnailUrl = thumbnail.path + "." + thumbnail.extension
    )
}