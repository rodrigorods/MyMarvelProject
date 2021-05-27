package com.rods.ui.character.view.detail

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rods.ui.character.view.navigation.CharacterDetailNavigation
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class CharacterDetailFragmentTest {

    private val navigation = mockk<CharacterDetailNavigation>()
    private val initialBundle = Bundle()

    @Before
    fun setup() {
        startKoin { modules(
            module {
                factory { navigation }
            })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun init_withNavigationInfo() {
        prepareScenario()

        launch(initialBundle) {
        } assert {
            assertName("nome od personagem")
            assertDescription("descrição do personagem")
        }
    }

    private fun prepareScenario() {
        every { navigation.init(initialBundle) } just runs
        every { navigation.getName() } returns "nome od personagem"
        every { navigation.getDescription() } returns "descrição do personagem"
        every { navigation.getThumbnailLink() } returns "link do thumbnail"
    }
}