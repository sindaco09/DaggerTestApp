package com.indaco.daggertestapp.landing

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.common.truth.Truth
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.data.repositories.UserRepository
import com.indaco.daggertestapp.data.storage.cache.UserCache
import com.indaco.daggertestapp.ui.screens.landing.LandingActivity
import com.indaco.daggertestapp.ui.screens.landing.LandingViewModel
import com.indaco.daggertestapp.ui.screens.landing.LandingViewModel_Factory
import com.indaco.daggertestapp.ui.screens.welcome.WelcomeActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@HiltAndroidTest
class LandingActivityTest {

    companion object {
        private const val EMAIL_VALID = "test@gmail.com"
    }

    @get: Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    // Rule represents an Activity in this case (renamed to ActivityScenarioExtension in JUnit5)
    @get: Rule(order = 1)
    var scenarioRule = ActivityScenarioRule(LandingActivity::class.java)

    @BindValue @Mock
    lateinit var userCache: UserCache

    @Before
    fun setup() {
        hiltRule.inject()
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun isNotLoggedIn() {
        `when`(userCache.currentUser).thenReturn(null)
//        `when`(landingViewModel.isLoggedIn()).thenReturn(Pair(false, null))
        scenarioRule.scenario.onActivity {
//            val isLoggedIn = landingViewModel.isLoggedIn()
//            Truth.assertThat(isLoggedIn)
        }
    }

    @Test
    fun isLoggedIn() {
        `when`(userCache.currentUser).thenReturn(User(EMAIL_VALID))
//        `when`(landingViewModel.isLoggedIn()).thenReturn(Pair(true, User(EMAIL_VALID)))
        scenarioRule.scenario.onActivity {
//            val isLoggedIn = landingViewModel.isLoggedIn()
//            Truth.assertThat(isLoggedIn)

            intended(hasComponent(WelcomeActivity::class.qualifiedName))
        }
    }
}