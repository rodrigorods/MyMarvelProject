package com.rods.ui.character.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.character.usecase.CharactersUseCase
import com.rods.domain.utils.ErrorResponse
import com.rods.domain.utils.ResultWrapper
import kotlinx.coroutines.*

sealed class UIState {
    object Waiting : UIState()
    object DisplayingUI : UIState()
    data class DefaultError(val error: ErrorResponse?) : UIState()
    object NetworkError : UIState()
    object PaginationError : UIState()
}

class CharacterListViewModel(
    private val context: Context,
    private val useCase: CharactersUseCase
): ViewModel() {

    private val _marvelCharacters = MutableLiveData<CharactersPage?>()
    val marvelCharacters: LiveData<CharactersPage?> = _marvelCharacters

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val batchSize = 10
    private var page = 0
    var searchTerm: String? = null
        set(value) {
            field = value
            resetLoadingData()
            loadInitialCharacters()
        }

    fun loadInitialCharacters() {
        if(_marvelCharacters.value == null) {
            _uiState.postValue(UIState.Waiting)
            loadCharacters(onError = {
                when (it) {
                    is ResultWrapper.GenericError -> _uiState.postValue(UIState.DefaultError(it.error))
                    is ResultWrapper.NetworkError -> _uiState.postValue(UIState.NetworkError)
                }
            })
        }
    }

    fun loadNextPage() {
        page++
        loadCharacters(onError = {
            page--
            _uiState.postValue(UIState.PaginationError)
        })
    }

    fun favoriteCharacter(c: MarvelCharacter) {
        viewModelScope.launch {
            useCase.favorite(c)
        }
    }

    private fun loadCharacters(
        onError : (ResultWrapper<CharactersPage>) -> Unit
    ) {
        viewModelScope.launch {
            val result = useCase.getCharacters(batchSize, page, searchTerm)
            if (result is ResultWrapper.Success) {
                dispatchMarvelCharacters(result.value)
            } else {
                if (!hasNetworkConnection()) onError(ResultWrapper.NetworkError)
                else onError(result)
            }
        }
    }

    private fun dispatchMarvelCharacters(value: CharactersPage) {
        _marvelCharacters.value?.characters?.let { currentCharacters ->
            value.characters.addAll(0, currentCharacters)
        }

        _marvelCharacters.postValue(value)
        _uiState.postValue(UIState.DisplayingUI)
    }

    fun resetLoadingData() {
        page = 0
        _marvelCharacters.value = null
    }

    @Suppress("DEPRECATION")
    private fun hasNetworkConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}