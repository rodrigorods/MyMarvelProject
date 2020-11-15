package com.rods.ui.character.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import com.rods.ui.character.R
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CharacterAdapterTest {

    private val adapter = CharacterAdapter()

    private val firstPositionMarvelCharacter = mockk<MarvelCharacter>()
    private val pageWithLoading = CharactersPage(
        hasMorePages = true,
        characters = mutableListOf(firstPositionMarvelCharacter, mockk(), mockk())
    )
    private val pageDefault = CharactersPage(
        hasMorePages = false,
        characters = mutableListOf(firstPositionMarvelCharacter, mockk(), mockk())
    )

    @Test
    fun onCreateViewHolder() {
        val mockParent = mockk<ViewGroup>()
        val mockView = mockk<TextView>()
        mockStaticLayoutInflation(mockParent, mockView, R.layout.character_cell)
        val viewHolder = adapter.onCreateViewHolder(mockParent, 1)

        assertTrue(viewHolder is CharacterAdapter.MarvelCharacterViewHolder)
        assertEquals((viewHolder as CharacterAdapter.MarvelCharacterViewHolder).itemView, mockView)
    }

    @Test
    fun onCreateViewHolder_loading() {
        val mockParent = mockk<ViewGroup>()
        val mockView = mockk<TextView>()
        mockStaticLayoutInflation(mockParent, mockView, R.layout.loading_cell)
        val viewHolder = adapter.onCreateViewHolder(mockParent, 0)

        assertTrue(viewHolder is CharacterAdapter.LoadingViewHolder)
        assertEquals((viewHolder as CharacterAdapter.LoadingViewHolder).itemView, mockView)
    }

    @Test
    fun onBindViewHolder_defaultViewHolder() {
        val viewHolder = mockk<CharacterAdapter.MarvelCharacterViewHolder>()
        every { viewHolder.bind(firstPositionMarvelCharacter, 0) } just runs

        adapter.insertCharacters(pageWithLoading)
        adapter.onBindViewHolder(viewHolder, 0)

        verify { viewHolder.bind(firstPositionMarvelCharacter, 0) }
    }

    @Test
    fun getItemViewType_returnDefaultType() {
        adapter.insertCharacters(pageWithLoading)
        assertEquals(adapter.getItemViewType(0), 1)
        assertEquals(adapter.getItemViewType(1), 1)
        assertEquals(adapter.getItemViewType(2), 1)
    }

    @Test
    fun getItemViewType_returnLoadingTypeForLastView() {
        adapter.insertCharacters(pageWithLoading)
        assertEquals(adapter.getItemViewType(3), 0)
    }

    @Test
    fun getItemCount_withLoading() {
        adapter.insertCharacters(pageWithLoading)
        assertEquals(adapter.itemCount, 4)
    }

    @Test
    fun getItemCount_noLoadingFinalPage() {
        adapter.insertCharacters(pageDefault)
        assertEquals(adapter.itemCount, 3)
    }

    private fun mockStaticLayoutInflation(
        mockParent: ViewGroup,
        mockView: View,
        layoutId: Int
    ) {
        mockkStatic(LayoutInflater::class)
        every { LayoutInflater.from(any()) } returns mockk {
            every { inflate(layoutId, mockParent, any()) } returns mockView
        }

        every { mockParent.context } returns mockk()
        every { mockView.findViewById<TextView>(any()) } returns mockk()
        every { mockView.findViewById<ImageView>(R.id.thumbnail) } returns mockk()
        every { mockView.findViewById<ImageView>(R.id.favoriteBtn) } returns mockk()
    }
}