package com.indaco.daggertestapp.ui.screens.landing

import android.app.Application
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.core.hilt.modules.CacheModule
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.data.storage.cache.UserCache
import com.indaco.daggertestapp.ui.screens.lazyActivityScenarioRule
import com.indaco.daggertestapp.ui.screens.welcome.WelcomeActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@UninstallModules(CacheModule::class)
@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
class LandingActivityTest {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), LandingActivity::class.java)

    @BindValue
    @JvmField
    var mockUserCache: UserCache = mockk(relaxed = true)

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val scenarioRule = lazyActivityScenarioRule<LandingActivity>(launchActivity = false)

    private fun launchWithHilt(everyFunc: (() -> Unit)? = null) {
        everyFunc?.invoke()
        hiltRule.inject()
        scenarioRule.launch(intent)
    }

    @Test
    fun test_value_true() {
        launchWithHilt { every { mockUserCache.testValue } returns true }

        scenarioRule.getScenario().onActivity {
            assertThat(it.binding.testValue.text.toString())
                .isEqualTo(it.getString(R.string.test_value_true))
        }
    }

    @Test
    fun test_value_false() {
        launchWithHilt { every { mockUserCache.testValue } returns false }

        scenarioRule.getScenario().onActivity {
            assertThat(it.binding.testValue.text.toString())
                .isEqualTo(it.getString(R.string.test_value_false))
        }
    }

    @Test
    fun isNotLoggedIn() {
        launchWithHilt { every { mockUserCache.currentUser } returns null }

        // Can access bindings via scenario
        scenarioRule.getScenario().onActivity {
            assertThat(it.binding.loginStatus.text.toString())
                .isEqualTo(it.getString(R.string.login_status_false))
        }
    }

    @Test
    fun isLoggedIn() {
        launchWithHilt { every { mockUserCache.currentUser } returns User("email@gmail.com") }

        scenarioRule.getScenario().onActivity {
            assertThat(it.binding.loginStatus.text.toString())
                .isEqualTo(it.getString(R.string.login_status_success))
        }

    }

    @Test
    fun isLoggedInStartActivity() {
        launchWithHilt { every { mockUserCache.currentUser } returns User("email@gmail.com") }

        scenarioRule.getScenario().onActivity {
            val expectedIntent = Intent(it, WelcomeActivity::class.java)
            val actualIntent = Shadows.shadowOf(ApplicationProvider.getApplicationContext() as Application).nextStartedActivity
            Assert.assertEquals(expectedIntent.component, actualIntent.component)
        }

    }
}