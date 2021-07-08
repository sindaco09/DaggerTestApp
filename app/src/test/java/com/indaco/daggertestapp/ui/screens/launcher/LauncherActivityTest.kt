package com.indaco.daggertestapp.ui.screens.launcher

import android.content.Intent
import com.indaco.daggertestapp.ui.screens.landing.LandingActivity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class LauncherActivityTest {

    @Test
    fun shouldStartNextActivity() {
        val activity = Robolectric.setupActivity(LauncherActivity::class.java)

        val expectedIntent = Intent(activity, LandingActivity::class.java)
        val actualIntent = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actualIntent.component)
    }
}