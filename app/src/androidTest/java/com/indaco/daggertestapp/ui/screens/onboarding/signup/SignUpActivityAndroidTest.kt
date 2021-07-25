package com.indaco.daggertestapp.ui.screens.onboarding.signup

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.indaco.daggertestapp.hilt.lazyActivityScenarioRule
import com.indaco.daggertestapp.ui.screens.onboarding.signup.email.EmailFragment
import com.indaco.daggertestapp.ui.screens.onboarding.signup.password.PasswordFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@SmallTest
class SignUpActivityAndroidTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val scenarioRule = lazyActivityScenarioRule<SignUpParentActivity>(launchActivity = true)

    private fun launchHiltActivityWithMocks(everyFunc: (() -> Unit)? = null) {
        everyFunc?.invoke()
        hiltRule.inject()
    }

    @Test
    fun emailFragment_active() {
        launchHiltActivityWithMocks()
        scenarioRule.getScenario().onActivity {
            val fragment = it.supportFragmentManager.findFragmentById(it.binding.emailContainer.id)
            Truth.assertThat(fragment is EmailFragment).isTrue()
        }
    }

    @Test
    fun passwordFragment_inactive() {
        launchHiltActivityWithMocks()
        scenarioRule.getScenario().onActivity {
            val fragment = it.supportFragmentManager.findFragmentById(it.binding.passwordContainer.id)
            Truth.assertThat(fragment is PasswordFragment).isFalse()
        }
    }

    @Test
    fun registerButton_inactive() {
        launchHiltActivityWithMocks()
        scenarioRule.getScenario().onActivity {
            Truth.assertThat(it.binding.register.alpha).isLessThan(1f)
            Truth.assertThat(it.binding.register.hasOnClickListeners()).isFalse()
        }
    }
}