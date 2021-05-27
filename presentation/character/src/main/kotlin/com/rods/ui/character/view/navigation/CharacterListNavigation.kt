package com.rods.ui.character.view.navigation

import com.rods.ui.character.view.CharacterListFragment

interface CharacterListNavigation {
    fun openCharacterDetail(fragment: CharacterListFragment,
                            characterName: String,
                            characterDescription: String,
                            characterThumbLink: String)
}