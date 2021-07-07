package com.indaco.daggertestapp.ui.screens.landing

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.core.hilt.DebugAllOpen
import com.indaco.daggertestapp.databinding.ActivityLandingBinding
import com.indaco.daggertestapp.ui.base.Base
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.ui.screens.login.LoginActivity
import com.indaco.daggertestapp.ui.screens.signup.SignUpActivity
import com.indaco.daggertestapp.ui.screens.welcome.WelcomeActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@DebugAllOpen
class LandingActivity : Base<ActivityLandingBinding>() {

    private val viewModel: LandingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingContentView(ActivityLandingBinding.inflate(layoutInflater))

        init()
    }

    private fun init() {
        initView()

        checkIfLoggedIn()

        checkTestValue()
    }

    private fun checkTestValue() {
        if (viewModel.getTestValue())
            binding.testValue.text = getString(R.string.test_value_true)
        else
            binding.testValue.text = getString(R.string.test_value_false)

    }

    private fun checkIfLoggedIn() =
        with(viewModel.isLoggedIn()) {
            if (first) {
                binding.loginStatus.text = getString(R.string.login_status_success)
                goToWelcomeScreen(second!!)
            } else {
                binding.loginStatus.text = getString(R.string.login_status_false)
            }
        }

    private fun goToWelcomeScreen(user: User) =
        startActivity(Intent(this, WelcomeActivity::class.java)
            .putExtra(User.KEY, user))

    private fun initView() =
        with(binding) {
            login.setOnClickListener { goToLoginScreen() }
            signup.setOnClickListener { goToSignUpScreen() }
        }

    private fun goToSignUpScreen() =
        startActivity(Intent(this, SignUpActivity::class.java))

    private fun goToLoginScreen() =
        startActivity(Intent(this,LoginActivity::class.java))

}