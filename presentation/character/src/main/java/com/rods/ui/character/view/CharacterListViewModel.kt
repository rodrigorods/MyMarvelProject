package com.rods.ui.character.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rods.domain.character.usecase.CharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val useCase: CharactersUseCase
): ViewModel() {

    fun loadCharacters() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getCharacters()
    }

}