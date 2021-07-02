package com.indaco.daggertestapp.landing

import androidx.test.core.app.ActivityScenario
import com.indaco.daggertestapp.di.DaggerTestInjector
import com.indaco.daggertestapp.di.TestAppComponent
import com.indaco.daggertestapp.ui.screens.landing.LandingActivity
import de.mannodermaus.junit5.ActivityScenarioExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.api.Assertions.assertTrue

class LandingActivityTest {

    @JvmField
    @RegisterExtension
    val scenarioExtension = ActivityScenarioExtension.launch<LandingActivity>()

    private lateinit var testAppComponent: TestAppComponent

    @BeforeEach
    fun setup() {
        DaggerTestInjector.getAppComponent()?.inject(this)
    }

    @Test
    @DisplayName("Test if logged in")
    fun isLoggedIn(scenario: ActivityScenario<LandingActivity>) {
        scenario.onActivity {
            val isLoggedIn = it.viewModel.isLoggedIn()

            assertTrue({ return@assertTrue isLoggedIn.first }, "should have been false" )
        }

    }
}