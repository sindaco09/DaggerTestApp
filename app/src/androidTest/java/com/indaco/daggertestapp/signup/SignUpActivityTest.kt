package com.indaco.daggertestapp.signup

import org.junit.Before
import org.junit.Test

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


    @Before
    fun setup() {
//        DaggerTestInjector.getAppComponent()?.inject(this)
    }

    @Test
    fun userInfo_returns_no_info_by_default() {

    }
}