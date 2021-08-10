package com.indaco.daggertestapp.ui.screens.main.bart

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.SmallTest
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.core.espresso.TestCountingIdlingResource
import com.indaco.daggertestapp.util.MockInterceptor
import com.indaco.daggertestapp.hilt.lazyActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@SmallTest
class BartActivityAndroidTest {

    private val app = ApplicationProvider.getApplicationContext<Context>()
    private val intent = Intent(app, BartActivity::class.java)

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val scenarioRule = lazyActivityScenarioRule<BartActivity>(launchActivity = false)

    // Needed AFTER Intents.init() but before the test. apply any 'every' statements here
    private fun launchHiltActivityWithMocks(everyFunc: (() -> Unit)? = null) {
        everyFunc?.invoke()
        hiltRule.inject()
        scenarioRule.launch(intent)
    }

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(TestCountingIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(TestCountingIdlingResource.countingIdlingResource)
    }

    // Test API call using CountingIdlingResource
    // when CountingIdlingResource == 0, the system state is at 0.
    @Test
    fun get_stations_success() {
        MockInterceptor.interceptEndpoint("stn.aspx","get_stations_success.json", 200)
        launchHiltActivityWithMocks()

        onView(withId(R.id.fetch))
            .perform(click())

        onView(withId(R.id.recyclerView))
            .check { view, _ ->
                (view as RecyclerView).adapter!!.itemCount > 0
            }
    }

    // Test API call using CountingIdlingResource
    // when CountingIdlingResource == 0, the system state is at 0.
    @Test
    fun get_stations_fail() {
        MockInterceptor.interceptEndpoint("stn.aspx","get_stations_fail.json", 400)
        launchHiltActivityWithMocks()

        onView(withId(R.id.fetch))
            .perform(click())

        onView(withText(app.getString(R.string.error_title)))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}