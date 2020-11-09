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

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val offset = 10
    private var page = 0

    fun loadCharacters() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = useCase.getCharacters(offset, page)) {
            is ResultWrapper.Success -> _marvelCharacters.postValue(result.value)
            else -> _error.postValue(0)
        }

    }

    fun loadNextPage() {
        page++
        loadCharacters()
    }

}