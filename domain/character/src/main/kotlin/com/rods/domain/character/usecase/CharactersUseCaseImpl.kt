package com.rods.domain.character.usecase

import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.character.repository.CharactersRepository
import com.rods.domain.utils.ResultWrapper

interface CharactersUseCase {
    suspend fun getCharacters(batchSize: Int, pageIndex: Int, searchTerm: String?): ResultWrapper<CharactersPage>
    suspend fun getFavoriteCharacters(): ResultWrapper<CharactersPage>
    suspend fun favorite(character: MarvelCharacter): ResultWrapper<Unit>
}

class CharactersUseCaseImpl(
    private val repository: CharactersRepository
): CharactersUseCase {
    override suspend fun getCharacters(
        batchSize: Int,
        pageIndex: Int,
        searchTerm: String?
    ): ResultWrapper<CharactersPage> {
        return repository.getCharacters(batchSize, getOffset(batchSize, pageIndex), searchTerm)
    }

    override suspend fun getFavoriteCharacters(): ResultWrapper<CharactersPage> {
        return repository.getFavoriteCharacters()
    }

    override suspend fun favorite(character: MarvelCharacter) = with(repository) {
        if (character.favorited) unfavorite(character) else favorite(character)
    }

    private fun getOffset(batchSize: Int, pageIndex: Int) = batchSize * pageIndex
}