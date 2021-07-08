package com.indaco.daggertestapp.ui.screens.launcher

import android.app.Application
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import com.indaco.daggertestapp.ui.screens.landing.LandingActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class LauncherActivityTest {

    @Test
    fun shouldStartNextActivity() {
        val scenario = launchActivity<LauncherActivity>()

        scenario.onActivity {
            val expectedIntent = Intent(it, LandingActivity::class.java)
            val actualIntent = shadowOf(ApplicationProvider.getApplicationContext() as Application).nextStartedActivity
            assertEquals(expectedIntent.component, actualIntent.component)
        }
    }
}