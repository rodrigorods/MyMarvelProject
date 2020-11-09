package com.rods.domain.character.model

data class CharactersPage(
    val hasMorePages: Boolean,
    val characters: MutableList<MarvelCharacter>
)