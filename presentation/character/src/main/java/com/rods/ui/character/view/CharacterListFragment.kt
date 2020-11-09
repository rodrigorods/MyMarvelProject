package com.rods.ui.character.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rods.ui.character.R
import com.rods.ui.character.view.adapter.CharacterAdapter
import com.rods.ui.character.view.navigation.CharacterListNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.characters_list_fragment.*

class CharacterListFragment: Fragment(R.layout.characters_list_fragment) {
    private val viewModel: CharacterListViewModel by viewModel()
    private val navigation: CharacterListNavigation by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadCharacters()
        observeData()
    }

    private fun observeData() {
        viewModel.marvelCharacters.observe(viewLifecycleOwner, {
            character_list.adapter = CharacterAdapter(it)
        })
    }
}