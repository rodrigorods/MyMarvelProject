package com.rods.ui.character.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.character.usecase.CharactersUseCase
import com.rods.domain.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val useCase: CharactersUseCase
): ViewModel() {

    private val _marvelCharacters = MutableLiveData<List<MarvelCharacter>>()
    val marvelCharacters: LiveData<List<MarvelCharacter>> = _marvelCharacters

    fun loadCharacters() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = useCase.getCharacters()) {
            is ResultWrapper.Success -> _marvelCharacters.postValue(result.value)
            else -> {}
        }

    }

}