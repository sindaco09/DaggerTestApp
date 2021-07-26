package com.indaco.daggertestapp.ui.screens.onboarding.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.databinding.ActivityWelcomeBinding
import com.indaco.daggertestapp.ui.base.BaseActivity
import com.indaco.daggertestapp.ui.screens.main.bart.BartActivity
import com.indaco.daggertestapp.ui.screens.onboarding.landing.LandingActivity
import dagger.hilt.android.AndroidEntryPoint

/*
 * Testing
 * + data passed via intent
 * + UI
 */
@AndroidEntryPoint
class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>() {

    private val viewModel: WelcomeViewModel by viewModels()

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingContentView(ActivityWelcomeBinding.inflate(layoutInflater))

        init()
    }

    private fun init() {
        setIntentData()

        binding.logout.setOnClickListener { logout() }

        binding.proceed.setOnClickListener { proceed() }
    }

    private fun proceed() {
        startActivity(Intent(this, BartActivity::class.java))
    }

    private fun setIntentData() {
        user = intent.getParcelableExtra(User.KEY)!!

        val welcomeMessage = getString(R.string.welcome_message, user.email)

        binding.welcomeTitle.text = welcomeMessage
    }

    private fun logout() {
        viewModel.logout()

        startActivity(Intent(this, LandingActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }
}