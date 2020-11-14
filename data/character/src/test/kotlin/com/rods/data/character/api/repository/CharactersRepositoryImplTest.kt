package com.rods.data.character.api.repository

import com.rods.data.character.api.datasource.CharacterDataSourceProvider
import com.rods.data.character.api.datasource.local.CharacterLocalDataSource
import com.rods.data.character.api.datasource.remote.CharacterRemoteDataSource
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersRepositoryImplTest {

    private val dataSourceProvider = mockk<CharacterDataSourceProvider>()
    private val remoteDataSource = mockk<CharacterRemoteDataSource>()
    private val localDataSource = mockk<CharacterLocalDataSource>()

    private val repository = CharactersRepositoryImpl(
        dataSourceProvider
    )

    @Before
    fun setup() {
        every { dataSourceProvider.getLocal() } returns localDataSource
        every { dataSourceProvider.getRemote() } returns remoteDataSource
        coEvery {
            remoteDataSource.getCharacters(10, 10, "any")
        } returns defaultCharactersPage
    }

    @Test
    fun getRemoteCharacters_returnCharactersPage() = runBlockingTest {
        val favoritePage = CharactersPage(false, mutableListOf(
            secondCharacter
        ))
        coEvery { localDataSource.getFavoriteCharacters() } returns favoritePage

        val page = repository.getCharacters(10, 10, "any")
        assertEquals(page, ResultWrapper.Success(CharactersPage(
            hasMorePages = false,
            characters = mutableListOf(
                MarvelCharacter(
                    id = 112,
                    name = "O Homem Homem",
                    description = "homem mordido por um homem radioativo",
                    thumbnailUrl = "URL",
                    favorited = false
                ),
                MarvelCharacter(
                    id = 211,
                    name = "Cavalo Humano",
                    description = "cavalo mordido por um homem radioativo",
                    thumbnailUrl = "URL",
                    favorited = true
                )
            )
        )))
    }

    @Test
    fun getRemoteCharacters_returnExpectedCharactersPageWithoutFavorites() = runBlockingTest {
        val noFavoritePage = CharactersPage(false, mutableListOf())
        coEvery { localDataSource.getFavoriteCharacters() } returns noFavoritePage

        val page = repository.getCharacters(10, 10, "any")
        assertEquals(page, ResultWrapper.Success(defaultCharactersPage))
    }

    @Test
    fun getFavoriteCharacters_returnCharactersPage() = runBlockingTest {
        val expectedCharactersPage = mockk<CharactersPage>()
        coEvery { localDataSource.getFavoriteCharacters() } returns expectedCharactersPage

        val page = repository.getFavoriteCharacters()
        assertEquals(page, ResultWrapper.Success(expectedCharactersPage))
    }

    private val firstCharacter = MarvelCharacter(
        id = 112,
        name = "O Homem Homem",
        description = "homem mordido por um homem radioativo",
        thumbnailUrl = "URL",
        favorited = false
    )
    private val secondCharacter = MarvelCharacter(
        id = 211,
        name = "Cavalo Humano",
        description = "cavalo mordido por um homem radioativo",
        thumbnailUrl = "URL",
        favorited = false
    )
    private val defaultCharactersPage = CharactersPage(
        hasMorePages = false,
        characters = mutableListOf(
            firstCharacter, secondCharacter
        )
    )
}