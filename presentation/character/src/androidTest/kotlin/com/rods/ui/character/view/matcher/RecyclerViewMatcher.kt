package com.rods.ui.character.view.matcher

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun matchChildPosition(childId: Int, position: Int, childMatcher: Matcher<View>) : Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("Checks if the matcher matches view inside RecyclerView")
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            val viewHolder = item?.findViewHolderForAdapterPosition(position)
            val matcher = hasDescendant(allOf(withId(childId), childMatcher))
            return viewHolder != null && matcher.matches(viewHolder.itemView)
        }
    }
}
