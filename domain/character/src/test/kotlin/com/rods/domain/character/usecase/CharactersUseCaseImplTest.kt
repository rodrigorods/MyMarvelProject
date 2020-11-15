package com.rods.domain.character.usecase

import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import com.rods.domain.character.repository.CharactersRepository
import com.rods.domain.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersUseCaseImplTest {

    private val character = mockk<MarvelCharacter>()
    private val marvelPage = mockk<CharactersPage>()

    private val repository = mockk<CharactersRepository>()
    private val useCase = CharactersUseCaseImpl(repository)

    @Test
    fun getCharacters_returnExpectedPageOfCharacters() = runBlockingTest {
        coEvery {
            repository.getCharacters(12, 24, "any")
        } returns ResultWrapper.Success(marvelPage)


        val response = useCase.getCharacters(batchSize = 12, pageIndex = 2, searchTerm = "any")
        assertEquals(response, ResultWrapper.Success(marvelPage))
    }

    @Test
    fun favorite_callUnfavoriteIfCharacterIsFavorite() = runBlockingTest {
        every { character.favorited } returns true
        coEvery { repository.unfavorite(character) } returns mockk()

        useCase.favorite(character)
        coVerify(exactly = 1) { repository.unfavorite(character) }
    }

    @Test
    fun favorite_callFavoriteIfCharacterIsNotFavorite() = runBlockingTest {
        every { character.favorited } returns false
        coEvery { repository.favorite(character) } returns mockk()

        useCase.favorite(character)
        coVerify(exactly = 1) { repository.favorite(character) }
    }
}