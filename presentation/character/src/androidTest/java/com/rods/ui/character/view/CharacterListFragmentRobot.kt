package com.rods.ui.character.view

import androidx.fragment.app.testing.launchFragmentInContainer
import com.rods.ui.character.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rods.ui.character.view.matcher.matchChildPosition
import org.hamcrest.Matchers.not

fun CharacterListFragmentTest.launch(
    invoke: CharacterListFragmentRobot.() -> Unit
): CharacterListFragmentRobot {
    launchFragmentInContainer<CharacterListFragment>(
        themeResId = R.style.Theme_MaterialComponents
    )

    return CharacterListFragmentRobot().apply(invoke)
}

class CharacterListFragmentRobot {

    infix fun assert(invoke: Assertion.() -> Unit) = Assertion().apply(invoke)

    inner class Assertion {

        fun checkNameAtPosition(textToMatch: String, position: Int) {
            onView(withId(R.id.character_list)).check(matches(
                matchChildPosition(R.id.name, position, withText(textToMatch))
            ))
        }

        fun checkDescriptionAtPosition(textToMatch: String, position: Int) {
            onView(withId(R.id.character_list)).check(matches(
                matchChildPosition(R.id.description, position, withText(textToMatch))
            ))
        }

        fun checkAdapterSize(size: Int) {
            onView(withId(R.id.character_list)).check(matches(
                hasChildCount(size)
            ))
        }

        fun checkLoadingIsDisplayed() {
            onView(withId(R.id.character_list)).check(matches(hasDescendant(withId(R.id.loading))))
        }

        fun checkLoadingIsNotDisplayed() {

            onView(withId(R.id.character_list)).check(matches(not(hasDescendant(withId(R.id.loading)))))
        }

    }
}