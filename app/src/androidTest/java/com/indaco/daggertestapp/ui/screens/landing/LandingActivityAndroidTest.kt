package com.indaco.daggertestapp.ui.screens.landing

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.SmallTest
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.core.hilt.modules.CacheModule
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.data.storage.cache.UserCache
import com.indaco.daggertestapp.hilt.lazyActivityScenarioRule
import com.indaco.daggertestapp.ui.screens.welcome.WelcomeActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

/* CacheModule is uninstalled and replaced with mock via @BindValue to manipulate data
 * received by activity
 */
@UninstallModules(CacheModule::class)
@HiltAndroidTest
@SmallTest
class LandingActivityAndroidTest {

    companion object {
        private const val EMAIL_VALID = "test@gmail.com"
    }

    private val intent = Intent(ApplicationProvider.getApplicationContext(), LandingActivity::class.java)

    // It is easier to mock data source than the viewmodel
    @BindValue
    @JvmField
    var mockUserCache: UserCache = mockk(relaxed = true)

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    // renamed to ActivityScenarioExtension in JUnit5
    @get: Rule(order = 1)
    val scenarioRule = lazyActivityScenarioRule<LandingActivity>(launchActivity = false)

    @Before
    fun setup() {
        Intents.init()
        every { mockUserCache.currentUser } returns null
    }

    // Needed AFTER Intents.init() but before the test. apply any 'every' statements here
    private fun launchHiltActivityWithMocks(everyFunc: (() -> Unit)? = null) {
        everyFunc?.invoke()
        hiltRule.inject()
        scenarioRule.launch(intent)
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun test_value_true() {
        launchHiltActivityWithMocks { every { mockUserCache.testValue } returns true }

        scenarioRule.getScenario().onActivity {
            assertEquals(expected = it.getString(R.string.test_value_true),
                actual = it.binding.testValue.text.toString())
        }
    }

    @Test
    fun test_value_false() {
        launchHiltActivityWithMocks { every { mockUserCache.testValue } returns false }

        scenarioRule.getScenario().onActivity {
            assertEquals(expected = it.getString(R.string.test_value_false),
                actual = it.binding.testValue.text.toString())
        }
    }

    @Test
    fun isNotLoggedIn() {
        launchHiltActivityWithMocks { every { mockUserCache.currentUser } returns null }

        // Can access bindings via scenario
        scenarioRule.getScenario().onActivity {
            assertEquals(expected = it.getString(R.string.login_status_false),
                actual = it.binding.loginStatus.text.toString())
        }
    }

    @Test
    fun isLoggedIn() {
        launchHiltActivityWithMocks { every { mockUserCache.currentUser } returns User(EMAIL_VALID) }

        scenarioRule.getScenario().onActivity {
            assertEquals(expected = it.getString(R.string.login_status_success),
                actual = it.binding.loginStatus.text.toString())
        }
    }

    @Test
    fun goToWelcomeScreen() {
        launchHiltActivityWithMocks { every { mockUserCache.currentUser } returns User(EMAIL_VALID) }

        intended(hasComponent(WelcomeActivity::class.java.name))
    }
}