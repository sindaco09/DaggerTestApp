package com.indaco.daggertestapp.ui.screens.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.indaco.daggertestapp.core.di.DaggerInjector
import com.indaco.daggertestapp.data.model.AuthForm
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.databinding.ActivitySignUpBinding
import com.indaco.daggertestapp.ui.base.Base
import com.indaco.daggertestapp.ui.screens.welcome.WelcomeActivity
import com.indaco.daggertestapp.util.Validator
import com.indaco.daggertestapp.util.Validator.inputIsValid
import javax.inject.Inject

class SignUpActivity : Base<ActivitySignUpBinding>() {

    @Inject lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingContentView(ActivitySignUpBinding.inflate(layoutInflater))

        DaggerInjector.Instance.getAppComponent()!!.inject(this)

        init()
    }

    private fun init() {
        initViews()

        observeData()
    }

    private fun initViews() {
        binding.register.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (fieldsAreValid())
                register(email, password)
        }
    }

    private fun fieldsAreValid(): Boolean {
        val isEmailValid = binding.emailLayout.inputIsValid(Validator.Type.EMAIL)
        val isPasswordValid = binding.passwordLayout.inputIsValid(Validator.Type.PASSWORD)
        return isEmailValid && isPasswordValid
    }

    private fun register(email: String, password: String) {
        viewModel.register(AuthForm(email, password))
    }

    private fun observeData() {
        viewModel.registerResult.observe(this) {
            if (it != null)
                goToMainScreen(it)
            else
                showError()
        }
    }

    private fun showError() = Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

    private fun goToMainScreen(user: User) =
        startActivity(Intent(this, WelcomeActivity::class.java)
            .putExtra(User.KEY, user))

}