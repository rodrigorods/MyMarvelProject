package com.rods.data.character.api.datasource.local

import com.rods.data.character.api.database.dao.CharacterDao
import com.rods.data.character.api.database.entity.CharacterEntity
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterLocalDataSourceImplTest {

    private val dao = mockk<CharacterDao>()
    private val localDataSource = CharacterLocalDataSourceImpl(dao)

    @Before
    fun setup() {
        coEvery { dao.unfavorite(any()) } just runs
        coEvery { dao.addToFavorite(any()) } just runs
        coEvery { dao.findAll() } returns listOf(characterEntity, characterEntity)
    }

    @Test
    fun getFavoriteCharacters() = runBlockingTest {
        val page = localDataSource.getFavoriteCharacters()
        assertEquals(CharactersPage(
            hasMorePages = false,
            mutableListOf(marvelCharacter, marvelCharacter)
        ), page)
    }

    @Test
    fun addCharacterToFavorite() = runBlockingTest {
        localDataSource.addCharacterToFavorite(marvelCharacter)
        coVerify(exactly = 1) {
            dao.addToFavorite(characterEntity)
        }
    }

    @Test
    fun removeFromFavorite() = runBlockingTest {
        localDataSource.removeFromFavorite(marvelCharacter)
        coVerify(exactly = 1) { dao.unfavorite(444) }
    }

    private val characterEntity = CharacterEntity(
        id = 444,
        name = "MARVEL HERO",
        description = "MAYBE A SHIELD AGENT",
        thumbnailUrl = "THGUMBN"
    )

    private val marvelCharacter = MarvelCharacter(
        id = 444,
        name = "MARVEL HERO",
        description = "MAYBE A SHIELD AGENT",
        thumbnailUrl = "THGUMBN"
    )
}