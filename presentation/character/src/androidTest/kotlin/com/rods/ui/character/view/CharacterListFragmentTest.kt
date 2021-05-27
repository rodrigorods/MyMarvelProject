package com.rods.ui.character.view

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.character.usecase.CharactersUseCase
import com.rods.domain.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class CharacterListFragmentTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val useCase = mockk<CharactersUseCase>()

    @Before
    fun setup() {
        startKoin { modules(
            module {
                androidContext(context)
                factory { useCase }
                viewModel { CharacterListViewModel(get(), get()) }
            })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun onInit_displayInitialListOfCharacters() {
        prepareInitialLoadScenario()

        launch {
        } assert {
            checkNameAtPosition("O Homem Homem", 0)
            checkDescriptionAtPosition("mulher mordida por uma mulher radioativa", 1)
            checkAdapterSize(finalPage.characters.size)
            checkLoadingIsNotDisplayed()
        }
    }

    @Test
    fun onPagination_displayFirstTwoPagesOfCharacters() {
        preparePaginationScenario()

        launch {
        } assert {
            checkAdapterSize(finalPage.characters.size + initialPage.characters.size)
        }
    }

    private fun prepareInitialLoadScenario() {
        coEvery {
            useCase.getCharacters(10, 0, null)
        } returns ResultWrapper.Success(finalPage)
    }

    private fun preparePaginationScenario() {
        coEvery {
            useCase.getCharacters(10, 0, null)
        } returns ResultWrapper.Success(initialPage)

        coEvery {
            useCase.getCharacters(10, 1, null)
        } returns ResultWrapper.Success(finalPage)
    }

    private val defaultCharacter = MarvelCharacter(
        id = 112,
        name = "O Homem Homem",
        description = "homem mordido por um homem radioativo",
        thumbnailUrl = "URL",
        favorited = false
    )

    private val favoritedCharacter = MarvelCharacter(
        id = 221,
        name = "A Mulher mulher",
        description = "mulher mordida por uma mulher radioativa",
        thumbnailUrl = "URL",
        favorited = true
    )

    private val finalPage = CharactersPage(
        hasMorePages = false,
        characters = mutableListOf(defaultCharacter, favoritedCharacter)
    )

    private val initialPage = CharactersPage(
        hasMorePages = true,
        characters = mutableListOf(defaultCharacter)
    )
}