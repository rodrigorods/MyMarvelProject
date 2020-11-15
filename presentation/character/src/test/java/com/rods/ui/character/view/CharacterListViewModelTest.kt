package com.rods.ui.character.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.character.usecase.CharactersUseCase
import com.rods.domain.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterListViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val context = mockk<Context>()
    private val useCase = mockk<CharactersUseCase>()
    private val viewModel = CharacterListViewModel(context, useCase)

    private val marvelCharacter = mockk<MarvelCharacter>()
    private val characterPage = mockk<CharactersPage>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun loadCharacters_dispatchSuccess() {
        coEvery {
            useCase.getCharacters(10, 0, null)
        } returns ResultWrapper.Success(characterPage)

        testDispatcher.pauseDispatcher()
        viewModel.loadInitialCharacters()
        assertEquals(viewModel.uiState.value, UIState.Waiting)

        testDispatcher.resumeDispatcher()
        assertEquals(viewModel.uiState.value, UIState.DisplayingUI)
        assertEquals(viewModel.marvelCharacters.value, characterPage)
    }

    @Test
    fun loadCharacters_dispatchNetworkError() {
        mockMissingNetworkConnection(hasConnection = false)
        coEvery {
            useCase.getCharacters(any(), any(), any())
        } returns ResultWrapper.GenericError(null, null)

        viewModel.loadInitialCharacters()

        assertEquals(viewModel.uiState.value, UIState.NetworkError)
        assertEquals(viewModel.marvelCharacters.value, null)
    }

    @Test
    fun loadCharacters_dispatchDefaultError() {
        mockMissingNetworkConnection(hasConnection = true)
        coEvery {
            useCase.getCharacters(any(), any(), any())
        } returns ResultWrapper.GenericError(1234, null)

        viewModel.loadInitialCharacters()

        assertEquals(viewModel.uiState.value, UIState.DefaultError(null))
        assertEquals(viewModel.marvelCharacters.value, null)
    }

    @Test
    fun loadNextPage_dispatchSuccess() {
        coEvery {
            useCase.getCharacters(10, 1, null)
        } returns ResultWrapper.Success(characterPage)

        testDispatcher.pauseDispatcher()
        viewModel.loadNextPage()
        assertNotEquals(viewModel.uiState.value, UIState.Waiting)

        testDispatcher.resumeDispatcher()
        assertEquals(viewModel.uiState.value, UIState.DisplayingUI)
        assertEquals(viewModel.marvelCharacters.value, characterPage)
    }

    @Test
    fun searchTerm_dispatchSuccess() {
        coEvery {
            useCase.getCharacters(10, 0, "Homem-aranha")
        } returns ResultWrapper.Success(characterPage)

        testDispatcher.pauseDispatcher()
        viewModel.searchTerm = "Homem-aranha"
        assertEquals(viewModel.uiState.value, UIState.Waiting)

        testDispatcher.resumeDispatcher()
        assertEquals(viewModel.uiState.value, UIState.DisplayingUI)
        assertEquals(viewModel.marvelCharacters.value, characterPage)
    }

    @Test
    fun favorite_sendCharacterThroughUsecase() {
        coEvery { useCase.favorite(marvelCharacter) } returns ResultWrapper.Success(Unit)
        viewModel.favoriteCharacter(marvelCharacter)

        coVerify { useCase.favorite(marvelCharacter) }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    private fun mockMissingNetworkConnection(hasConnection: Boolean) {
        every { context.getSystemService(any()) } returns mockk<ConnectivityManager> {
            every { activeNetworkInfo } returns mockk {
                every { isConnectedOrConnecting } returns hasConnection
            }
        }
    }
}