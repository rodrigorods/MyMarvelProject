package com.rods.data.character.api.datasource.local

import com.rods.data.character.api.database.dao.CharacterDao
import com.rods.data.character.api.database.entity.CharacterEntity
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter

interface CharacterLocalDataSource {
    suspend fun getFavoriteCharacters(): CharactersPage
    suspend fun addCharacterToFavorite(character: MarvelCharacter)
    suspend fun removeFromFavorite(character: MarvelCharacter)
}

class CharacterLocalDataSourceImpl(
    private val dao: CharacterDao
): CharacterLocalDataSource {
    override suspend fun getFavoriteCharacters(): CharactersPage {
        val characters = dao.findAll()

        return CharactersPage (
            hasMorePages = false,
            characters = characters.map { it.toMarvelCharacter() }.toMutableList()
        )
    }

    override suspend fun addCharacterToFavorite(character: MarvelCharacter) {
        dao.addToFavorite(character.toEntity())
    }

    override suspend fun removeFromFavorite(character: MarvelCharacter) {
        dao.unfavorite(character.id)
    }

    private fun MarvelCharacter.toEntity() = CharacterEntity(
        id = id,
        name = name,
        description = description,
        thumbnailUrl = thumbnailUrl
    )

    private fun CharacterEntity.toMarvelCharacter() = MarvelCharacter(
        id = id,
        name = name,
        description = description,
        thumbnailUrl = thumbnailUrl,
        favorited = true
    )
}