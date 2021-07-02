package com.indaco.daggertestapp.ui.screens.welcome

import android.content.Intent
import android.os.Bundle
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.core.di.DaggerInjector
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.databinding.ActivityWelcomeBinding
import com.indaco.daggertestapp.ui.base.Base
import com.indaco.daggertestapp.ui.screens.landing.LandingActivity
import javax.inject.Inject

class WelcomeActivity : Base<ActivityWelcomeBinding>() {

    @Inject lateinit var viewModel: WelcomeViewModel

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingContentView(ActivityWelcomeBinding.inflate(layoutInflater))

        DaggerInjector.Instance.getAppComponent()!!.inject(this)

        init()
    }

    private fun init() {
        setIntentData()

        binding.logout.setOnClickListener { logout() }
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