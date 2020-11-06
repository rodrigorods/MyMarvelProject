package com.rods.domain.character.usecase

import com.rods.domain.character.repository.CharactersRepository

interface CharactersUseCase {
    suspend fun getCharacters()
}

class CharactersUseCaseImpl(
    private val repository: CharactersRepository
): CharactersUseCase {
    override suspend  fun getCharacters() {
        repository.getCharacters()
    }
}