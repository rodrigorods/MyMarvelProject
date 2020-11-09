package com.rods.domain.character.repository

import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.utils.ResultWrapper

interface CharactersRepository {
    suspend fun getCharacters(): ResultWrapper<List<MarvelCharacter>>
}