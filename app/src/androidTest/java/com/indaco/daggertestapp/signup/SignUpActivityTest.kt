package com.indaco.daggertestapp.signup

import com.indaco.daggertestapp.di.DaggerTestInjector
import com.indaco.daggertestapp.ui.screens.signup.SignUpActivity
import de.mannodermaus.junit5.ActivityScenarioExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import javax.inject.Inject

class SignupActivityTest {

    companion object {
        private const val BLANK = ""
        private const val EMAIL_VALID = "valid@gmail.com"
        private const val EMAIL_INVALID = "validgmailcom"

        private const val PASSWORD_SHORT = "aa"
        private const val PASSWORD_LONG = "aaaaaaaaaaa"
        private const val PASSWORD_INVALID = "p@ssw0r^|"
        private const val PASSWORD_VALID = "Aaaaa1"
        private val PASSWORDS = listOf(PASSWORD_SHORT, PASSWORD_LONG, PASSWORD_INVALID, PASSWORD_VALID, BLANK)

        var passedPWTests: Boolean = true
    }

    @JvmField
    @RegisterExtension
    val scenarioExtension = ActivityScenarioExtension.launch<SignUpActivity>()

    @BeforeEach
    fun setup() {
        DaggerTestInjector.getAppComponent()?.inject(this)
    }

    @Test
    fun userInfo_returns_no_info_by_default() {

    }
}