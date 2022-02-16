package com.indaco.login.ui.screens.login.hilt_login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.indaco.daggertestapp.core.hilt.viewmodel.ViewModelFactory
import com.indaco.daggertestapp.data.model.User
import com.indaco.login.databinding.ActivityHiltLoginBinding
import com.indaco.daggertestapp.ui.base.BaseActivity
import com.indaco.daggertestapp.ui.screens.onboarding.welcome.WelcomeActivity
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.data.repositories.UserRepository
import com.indaco.daggertestapp.data.storage.cache.UserSPCache
import com.indaco.login.di.Injector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
 * Hilt Activity to test:
 * + read values from Cache
 * + capture intent when going to another activity
 */

class HiltLoginActivity : BaseActivity<ActivityHiltLoginBinding>() {

    @Inject
    lateinit var userSPCache: UserSPCache

    companion object {
        private const val MIN = 3
        private const val MAX = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.inject(this)
        setBindingContentView(ActivityHiltLoginBinding.inflate(layoutInflater))

        init()
    }

    private fun init() {
        initViews()
    }

    private fun initViews() {
        binding.submit.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (fieldsAreValid(email, password))
                login(email)
        }
    }

    private fun fieldsAreValid(email: String?, password: String?): Boolean {
        val isEmailValid = isEmailEntryValid(email)
        val isPasswordValid = isPasswordValid(password)
        return isEmailValid && isPasswordValid
    }

    private fun isEmailEntryValid(email: String?): Boolean {
        val error =  when {
            email.isNullOrBlank() -> getString(R.string.error_email_blank)
            email.isEmailInvalid() -> getString(R.string.error_email_not_email_pattern)
            else ->  null
        }
        binding.emailLayout.error = error
        return error == null
    }

    private fun String.isEmailInvalid(): Boolean {
        return TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isPasswordValid(password: String?): Boolean {
        val error =  when {
            password.isNullOrBlank() -> getString(R.string.error_password_blank)
            password.length < MIN -> getString(R.string.error_password_short)
            password.length > MAX -> getString(R.string.error_password_long)
            password.any { !it.isLetterOrDigit() } -> getString(R.string.error_password_not_alphanumeric)
            else ->  null
        }
        binding.passwordLayout.error = error
        return error == null
    }

    private fun login(enteredEmail: String) {
        val email = userSPCache.email
        when {
            enteredEmail == email -> displayResult("emails match!: $email")
            email != null -> {
                userSPCache.email = enteredEmail
                displayResult("found email: $email")
            }
            else -> {
                userSPCache.email = enteredEmail
                displayResult("no email found")
            }
        }
    }

    private fun displayMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun displayResult(message: String) {
        binding.result.text = message
    }

}