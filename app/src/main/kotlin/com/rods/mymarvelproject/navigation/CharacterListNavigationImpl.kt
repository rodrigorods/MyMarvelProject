package com.rods.mymarvelproject.navigation

import androidx.navigation.fragment.findNavController
import com.rods.ui.character.view.CharacterListFragment
import com.rods.ui.character.view.CharacterListFragmentDirections
import com.rods.ui.character.view.navigation.CharacterListNavigation

class CharacterListNavigationImpl: CharacterListNavigation {
    override fun openCharacterDetail(fragment: CharacterListFragment,
                                     characterName: String,
                                     characterDescription: String,
                                     characterThumbLink: String
    ) {
        val navigation = CharacterListFragmentDirections.actionOpenDetailFragment(characterName, characterDescription, characterThumbLink)
        fragment.findNavController().navigate(navigation)
    }
}