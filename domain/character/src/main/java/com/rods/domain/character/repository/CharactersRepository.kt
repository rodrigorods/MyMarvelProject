package com.rods.domain.character.repository

interface CharactersRepository {
    suspend fun getCharacters()
}