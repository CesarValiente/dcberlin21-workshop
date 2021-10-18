package com.cesarvaliente.navigationrail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoRepository.Companion.windowInfoRepository
import androidx.window.layout.WindowLayoutInfo
import androidx.window.testing.layout.FoldingFeature
import androidx.window.testing.layout.WindowLayoutInfoPublisherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private val publisherRule = WindowLayoutInfoPublisherRule()

    private val testScope = TestCoroutineScope()

    @get:Rule
    val testRule: TestRule

    init {
        testRule = RuleChain.outerRule(publisherRule).around(activityRule)
    }

    @Test
    fun test_nav_rail_should_be_shown_on_foldable_device() = runBlockingTest {
        activityRule.scenario.onActivity { activity ->
            val hinge = FoldingFeature(
                activity = activity,
                state = FoldingFeature.State.FLAT,
                orientation = FoldingFeature.Orientation.VERTICAL,
                size = 2
            )
            val expected =
                WindowLayoutInfo.Builder().setDisplayFeatures(listOf(hinge)).build()

            val value = testScope.async {
                activity.windowInfoRepository().windowLayoutInfo.first()
            }
            publisherRule.overrideWindowLayoutInfo(expected)
            runBlockingTest {
                assertEquals(
                    expected,
                    value.await()
                )
            }
        }
        onView(withId(R.id.navigation_rail)).check(matches(isDisplayed()))
    }
}
