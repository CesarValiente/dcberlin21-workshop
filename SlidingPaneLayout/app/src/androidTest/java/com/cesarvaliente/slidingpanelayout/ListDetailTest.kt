package com.cesarvaliente.slidingpanelayout

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.cesarvaliente.slidingpanelayout.recyclerview.ItemViewHolder
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DualScreenTests {

    @get:Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun test_open_item_single_screen() {
        onView(withId(R.id.list_container)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_container)).check(matches(Matchers.not(isDisplayed())))

        val POSITION_TO_TEST = 2
        onView(withId(R.id.list_items))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ItemViewHolder>(
                    POSITION_TO_TEST,
                    ViewActions.click()
                )
            )

        onView(withId(R.id.detail_container)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_layout)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.mail_title), ViewMatchers.withText("Mail Title $POSITION_TO_TEST")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayListAndDetails_whenInDualMode() {
        spanApplication()

        onView(withId(R.id.list_container)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_container)).check(matches(isDisplayed()))

        onView(withId(R.id.list_items)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_view)).check(matches(isDisplayed()))
    }

    @Test
    fun item_in_detail_view_when_dual_screen_mode_is_shown_correctly() {
        spanApplication()

        onView(withId(R.id.list_container)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_container)).check(matches(isDisplayed()))

        val POSITION_TO_TEST = 2
        onView(withId(R.id.list_items))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ItemViewHolder>(
                    POSITION_TO_TEST,
                    ViewActions.click()
                )
            )
        onView(withId(R.id.detail_container)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_layout)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.mail_title), ViewMatchers.withText("Mail Title $POSITION_TO_TEST")))
            .check(matches(isDisplayed()))
    }

    private fun spanApplication() {
        device.swipe(675, 1780, 1350, 900, 400)
    }
}