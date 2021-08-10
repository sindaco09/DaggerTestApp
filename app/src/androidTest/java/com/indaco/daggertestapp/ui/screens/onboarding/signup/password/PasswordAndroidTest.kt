package com.indaco.daggertestapp.ui.screens.onboarding.signup.password

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.SmallTest
import com.google.android.material.textfield.TextInputLayout
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.core.hilt.modules.storage.CacheModule
import com.indaco.daggertestapp.data.storage.cache.UserCache
import com.indaco.daggertestapp.hilt.launchFragmentInHiltContainer
import com.indaco.daggertestapp.util.Const
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@UninstallModules(CacheModule::class)
@HiltAndroidTest
@SmallTest
class PasswordAndroidTest {

    // Represent the associated error with the entry: Pair<Entry, Error?>
    private val BLANK_PASSWORD = Pair(Const.BLANK, R.string.error_password_blank)
    private val SHORT_PASSWORD = Pair(Const.PASSWORD_SHORT, R.string.error_password_short)
    private val LONG_PASSWORD = Pair(Const.PASSWORD_LONG, R.string.error_password_long)
    private val INVALID_PASSWORD = Pair(Const.PASSWORD_INVALID, R.string.error_password_not_alphanumeric)
    private val VALID_PASSWORD = Pair(Const.PASSWORD_VALID, null)

    // allows access to values in res folder
    val res: Resources = ApplicationProvider.getApplicationContext<Context>().resources

    @BindValue
    @JvmField
    var mockUserCache: UserCache = mockk(relaxed = true)

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
        launchFragmentInHiltContainer<PasswordFragment>()
    }

    // Test Password field alone
    @Test
    fun blank_password() = passwordErrorMessageTest(BLANK_PASSWORD, false)

    @Test
    fun short_password() = passwordErrorMessageTest(SHORT_PASSWORD, false)

    @Test
    fun long_password() = passwordErrorMessageTest(LONG_PASSWORD, false)

    @Test
    fun invalid_password() = passwordErrorMessageTest(INVALID_PASSWORD, false)

    @Test
    fun valid_password() = passwordErrorMessageTest(VALID_PASSWORD, false)

    // Test Confirm field alone
    @Test
    fun blank_confirm_password() = passwordErrorMessageTest(BLANK_PASSWORD, true)

    @Test
    fun short_confirm_password() = passwordErrorMessageTest(SHORT_PASSWORD, true)

    @Test
    fun long_confirm_password() = passwordErrorMessageTest(LONG_PASSWORD, true)

    @Test
    fun invalid_confirm_password() = passwordErrorMessageTest(INVALID_PASSWORD, true)

    @Test
    fun valid_confirm_password() = passwordErrorMessageTest(VALID_PASSWORD, true)

    private fun passwordErrorMessageTest(entry: Pair<String, Int?>, isConfirmPasswordLayout: Boolean) {
        val inputFieldId = if (isConfirmPasswordLayout) R.id.confirm_password else R.id.password
        val inputLayoutFieldId = if (isConfirmPasswordLayout) R.id.confirm_password_layout else R.id.password_layout

        // Set-up: click on password/confirm password field, input text, and press "Next" button on keyboard
        Espresso.onView(ViewMatchers.withId(inputFieldId))
            .perform(ViewActions.typeText(entry.first))
            .perform(ViewActions.pressImeActionButton())

        val expectedError = if (entry.second != null) res.getString(entry.second!!) else null

        // test error message expected from InputTextLayout
        Espresso.onView(ViewMatchers.withId(inputLayoutFieldId)).check { view, _ ->
            val actualError = (view as TextInputLayout).error
            assertEquals(actualError, expectedError)
        }
    }

}