package com.indaco.daggertestapp.ui.screens.launcher

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.indaco.daggertestapp.lazyActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LauncherActivityTest {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), LauncherActivity::class.java)


    @get: Rule
    val rule = lazyActivityScenarioRule<LauncherActivity>(launchActivity = false)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun teardown() {
        Intents.release()
    }

    @Test
    fun goesToLandingScreen() {
        rule.launch(intent)

        intended(hasComponent(EmptyActivity::class.java.name))
    }

}