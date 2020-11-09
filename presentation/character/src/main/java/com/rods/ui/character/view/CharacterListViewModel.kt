package com.rods.ui.character.view

import androidx.lifecycle.*
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.usecase.CharactersUseCase
import com.rods.domain.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val useCase: CharactersUseCase
): ViewModel() {

    private val _marvelCharacters = MutableLiveData<CharactersPage>()
    val marvelCharacters: LiveData<CharactersPage> = _marvelCharacters

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val batchSize = 10
    private var page = 0

    fun loadCharacters() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = useCase.getCharacters(batchSize, page)) {
            is ResultWrapper.Success -> dispatchMarvelCharacters(result.value)
            else -> _error.postValue(0)
        }

    }

    private fun dispatchMarvelCharacters(value: CharactersPage) {
        _marvelCharacters.value?.characters?.let { currentCharacters ->
            value.characters.addAll(0, currentCharacters)
        }

        _marvelCharacters.postValue(value)
    }

    fun loadNextPage() {
        page++
        loadCharacters()
    }

}