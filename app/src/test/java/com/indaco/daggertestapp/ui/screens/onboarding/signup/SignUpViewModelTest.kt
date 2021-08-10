package com.indaco.daggertestapp.ui.screens.onboarding.signup

import android.content.Context
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.data.repositories.UserRepository
import com.indaco.daggertestapp.utils.Const
import com.indaco.daggertestapp.utils.InstantExecutorExtension
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor

/*
 * Testing using JUnit 5 instead of JUnit 4
 */
@ExperimentalCoroutinesApi // for TestCoroutineDispatcher
@ExtendWith(InstantExecutorExtension::class)
class SignUpViewModelTest {

    private val userRepository = mockk<UserRepository>()

    private val dispatcher = TestCoroutineDispatcher()

    private val emailInUse: Observer<Pair<Boolean, String?>> = mockk()

    lateinit var viewModel: SignUpViewModel

    @BeforeEach
    fun setup() {

        viewModel = SignUpViewModel(userRepository, dispatcher)
    }

    @Test
    fun `email is in use`() {
        val email = Const.EMAIL_VALID

        // Observe livedata
        viewModel.emailInUse.observeForever(emailInUse)

        // Used to capture the events of onChanged in emailInUse observer
        val slot = slot<Pair<Boolean,String?>>()

        every { emailInUse.onChanged(capture(slot)) }

        every { userRepository.isEmailInUse(email) } returns flowOf(true)

        // Actual Test
        viewModel.checkIfEmailInUse(email, mockk<Context>(relaxed = true).resources)

        Truth.assertThat(slot.captured.first).isTrue()
    }

    @Test
    fun `email is not in use`() {
        val email = Const.EMAIL_VALID

        // Observe livedata
        viewModel.emailInUse.observeForever(emailInUse)

        // Used to capture the events of onChanged in emailInUse observer
        val slot = slot<Pair<Boolean,String?>>()

        every { emailInUse.onChanged(capture(slot)) }

        every { userRepository.isEmailInUse(email) } returns flowOf(false)

        // Actual Test
        viewModel.checkIfEmailInUse(email, mockk<Context>(relaxed = true).resources)

        Truth.assertThat(slot.captured.first).isFalse()
    }
}