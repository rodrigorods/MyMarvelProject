package com.rods.data.character.api.repository

import com.rods.data.character.api.datasource.CharacterDataSource
import com.rods.data.character.api.model.CharactersResponse
import com.rods.data.utils.repository.safeApiCall
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.character.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val remoteDataSource: CharacterDataSource
): CharactersRepository {
    override suspend fun getCharacters(batchSize: Int, offset: Int) = safeApiCall {
        val response = remoteDataSource.getCharacters(batchSize, offset)
        CharactersPage (
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