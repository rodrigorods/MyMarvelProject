package com.rods.ui.character.view.detail

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import com.rods.ui.character.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

fun CharacterDetailFragmentTest.launch(
    initialBundle: Bundle,
    invoke: CharacterDetailFragmentRobot.() -> Unit
): CharacterDetailFragmentRobot {
    launchFragmentInContainer<CharacterDetailFragment>(
        fragmentArgs = initialBundle,
        themeResId = R.style.Theme_MaterialComponents
    )

    return CharacterDetailFragmentRobot().apply(invoke)
}

class CharacterDetailFragmentRobot {

    infix fun assert(invoke: Assertion.() -> Unit) = Assertion().apply(invoke)

    inner class Assertion {

        fun assertName(nameToMatch: String) {
            onView(withId(R.id.name)).check(matches(withText(nameToMatch)))
        }

        fun assertDescription(descriptionToMatch: String) {
            onView(withId(R.id.description)).check(matches(withText(descriptionToMatch)))
        }

    }
}