package com.indaco.daggertestapp.landing

import androidx.core.content.ContextCompat.startActivity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.ui.screens.landing.LandingActivity
import com.indaco.daggertestapp.ui.screens.landing.LandingViewModel
import com.indaco.daggertestapp.ui.screens.welcome.WelcomeActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//@UninstallModules(CacheModule::class)
@HiltAndroidTest
class LandingActivityTest {

    private val viewModel: LandingViewModel = mockk(relaxed = true)

    companion object {
        private const val EMAIL_VALID = "test@gmail.com"
    }

    @get: Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    // renamed to ActivityScenarioExtension in JUnit5
    @get: Rule(order = 1)
    var scenarioRule = ActivityScenarioRule(LandingActivity::class.java)

    @Before
    fun setup() {
        Intents.init()

        hiltRule.inject()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

//    @Test
//    fun isNotLoggedIn() {
//        every { viewModel.isLoggedIn() } returns Pair(false, null)
//        scenarioRule.scenario.onActivity {
//
//        }
//    }

    @Test
    fun isLoggedIn() {
        every { viewModel.isLoggedIn() } returns Pair(true, User(EMAIL_VALID))

        intended(hasComponent(LandingActivity::class.java.name))
    }
}