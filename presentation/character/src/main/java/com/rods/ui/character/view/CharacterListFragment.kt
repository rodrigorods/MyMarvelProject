package com.rods.ui.character.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rods.ui.character.R
import com.rods.ui.character.view.navigation.CharacterListNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment: Fragment(R.layout.characters_list_fragment) {
    private val viewModel: CharacterListViewModel by viewModel()
    private val navigation: CharacterListNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadCharacters()
    }
}