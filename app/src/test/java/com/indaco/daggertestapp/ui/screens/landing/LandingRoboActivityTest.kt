package com.indaco.daggertestapp.ui.screens.landing

import androidx.test.core.app.launchActivity
import com.google.common.truth.Truth
import com.indaco.daggertestapp.R
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
//@Config(shadows = [LandingViewModel::class])
class LandingRoboActivityTest {

    @Test
    fun test_value_true() {
        val viewModelMock = mockk<LandingViewModel>()

        val scenario = launchActivity<LandingActivity>()


        scenario.onActivity {



//            Truth.assertThat(it.binding.testValue.text.toString())
//                .isEqualTo(it.getString(R.string.test_value_true))
        }

        scenario.close()
    }
}