package com.rods.domain.character.usecase

import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.character.repository.CharactersRepository
import com.rods.domain.utils.ResultWrapper

interface CharactersUseCase {
    suspend fun getCharacters(batchSize: Int, pageIndex: Int): ResultWrapper<List<MarvelCharacter>>
}

class CharactersUseCaseImpl(
    private val repository: CharactersRepository
): CharactersUseCase {
    override suspend fun getCharacters(
        batchSize: Int,
        pageIndex: Int
    ) = repository.getCharacters(batchSize, getOffset(batchSize, pageIndex))


    private fun getOffset(batchSize: Int, pageIndex: Int) = batchSize * pageIndex
}