package com.example.dictionary

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.dictionary.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest
{
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)


    @Test
    fun useAppContext()
    {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.dictionary" , appContext.packageName)
    }


    @Test
    fun activityLaunch() {
        onView(withId(R.id.searchBtn)).perform(click())
        //onView(withId(R.id.searchText)).check(matches(isDisplayed()))
    }

    @Test
    fun textInputOutput() {
        onView(withId(R.id.searchText)).perform(typeText("test"))
        onView(withId(R.id.searchBtn)).perform(click())
        onView(withId(R.id.searchText)).check(matches(withText("test")))
    }

}
