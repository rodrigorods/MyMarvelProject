package com.rods.ui.character.view

import android.util.Log
import androidx.lifecycle.*
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.usecase.CharactersUseCase
import com.rods.domain.utils.ErrorResponse
import com.rods.domain.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class UIState {
    object Waiting : UIState()
    object DisplayingUI : UIState()
    data class DefaultError(val error: ErrorResponse?) : UIState()
    object NetworkError : UIState()
    object PaginationError : UIState()
}

class CharacterListViewModel(
    private val useCase: CharactersUseCase
): ViewModel() {

    private val _marvelCharacters = MutableLiveData<CharactersPage>()
    val marvelCharacters: LiveData<CharactersPage> = _marvelCharacters

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val batchSize = 10
    private var page = 0

    fun loadCharacters() = viewModelScope.launch(Dispatchers.IO) {
        _uiState.postValue(UIState.Waiting)

        val result = useCase.getCharacters(batchSize, page)
        val currentUiState = when (result) {
            is ResultWrapper.Success -> {
                dispatchMarvelCharacters(result.value)
                UIState.DisplayingUI
            }
            is ResultWrapper.GenericError -> UIState.DefaultError(result.error)
            else -> UIState.NetworkError
        }
        _uiState.postValue(currentUiState)
    }

    private fun dispatchMarvelCharacters(value: CharactersPage) {
        Log.e("TESTE", "dispatchMarvelCharacters: $page")
        _marvelCharacters.value?.characters?.let { currentCharacters ->
            value.characters.addAll(0, currentCharacters)
        }

        _marvelCharacters.postValue(value)
    }

    fun loadNextPage() {
        page++
        Log.e("TESTE", "loadNextPage: $page")
        loadCharacters()
    }

}