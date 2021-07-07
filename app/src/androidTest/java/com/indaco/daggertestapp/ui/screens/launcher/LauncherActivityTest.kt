package com.indaco.daggertestapp.ui.screens.launcher

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.indaco.daggertestapp.hilt.lazyActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LauncherActivityTest {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), LauncherActivity::class.java)

    // lazy is used because i need to record Intents via Intents.init() before the activity
    // is created. only other workaround is to start Intents.init() in init {} block
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
        // says to launch activity now with given intent
        rule.launch(intent)

        intended(hasComponent(EmptyActivity::class.java.name))
    }

}