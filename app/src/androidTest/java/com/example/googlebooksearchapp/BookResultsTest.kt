package com.example.googlebooksearchapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.LayoutAssertions.noOverlaps
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.CoreMatchers.allOf


import org.junit.Rule
import org.junit.Test


import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BookResultsTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_activity_in_view(){
        onView(withId(R.id.main)).check(matches(isDisplayed()))
        onView(withId(R.id.main)).check(noOverlaps())
    }

    @Test
    fun test_is_valid(){
        onView(allOf(withId(R.id.authorString), withText("Harry")))
    }

    @Test
    fun button_is_displayed() {
        onView(withId(R.id.button)).check(matches(isDisplayed()))
    }

    @Test
    fun button_is_clicked() {
        onView(withId(R.id.button)).perform(ViewActions.click())
        onView(withId(R.id.button)).check(matches(isEnabled()))
    }

    @Test
    fun testItemFound() {
        onView(allOf(withId(R.id.authorString), withText("Harry")))
        onView(allOf(withId(R.id.keywordString), withText("Science")))
        onView(withId(R.id.button)).perform(ViewActions.click())
//        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
//        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()))


    }




















}
