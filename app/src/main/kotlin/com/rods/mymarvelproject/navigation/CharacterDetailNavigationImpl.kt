package com.rods.mymarvelproject.navigation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.rods.ui.character.view.CharacterListFragment
import com.rods.ui.character.view.CharacterListFragmentDirections
import com.rods.ui.character.view.detail.CharacterDetailFragmentArgs
import com.rods.ui.character.view.navigation.CharacterDetailNavigation
import com.rods.ui.character.view.navigation.CharacterListNavigation

class CharacterDetailNavigationImpl: CharacterDetailNavigation {

    private lateinit var args: CharacterDetailFragmentArgs

    override fun init(bundle: Bundle) {
        args = CharacterDetailFragmentArgs.fromBundle(bundle)
    }
    override fun getName() = args.characterName
    override fun getDescription() = args.characterDescription
    override fun getThumbnailLink() = args.characterThumbLink
}