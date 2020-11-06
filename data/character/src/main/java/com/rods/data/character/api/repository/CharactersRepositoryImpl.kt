package com.rods.data.character.api.repository

import com.rods.data.character.api.datasource.CharacterDataSource
import com.rods.domain.character.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val remoteDataSource: CharacterDataSource
): CharactersRepository {
    override suspend fun getCharacters() {
        remoteDataSource.getCharacters()
    }
}