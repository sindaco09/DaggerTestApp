package com.indaco.daggertestapp.util

import android.content.Context
import android.text.TextUtils
import com.google.android.material.textfield.TextInputLayout
import com.indaco.daggertestapp.R

object Validator {

    enum class Type {EMAIL, PASSWORD}

    private const val PASSWORD_MIN = 3
    private const val PASSWORD_MAX = 10

    fun TextInputLayout.inputIsValid(type: Type): Boolean =
        when (type) {
            Type.EMAIL -> isEmailEntryValid(context, editText.toString(), this)
            Type.PASSWORD -> isPasswordValid(context, editText.toString(), this)
        }

    private fun isEmailEntryValid(context: Context, email: String?, errorLayout: TextInputLayout): Boolean {
        val error =  when {
            email.isNullOrBlank() -> context.getString(R.string.error_email_blank)
            email.isEmailInvalid() -> context.getString(R.string.error_email_not_email_pattern)
            else ->  null
        }
        errorLayout.error = error
        return error == null
    }

    private fun String.isEmailInvalid(): Boolean =
        TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun isPasswordValid(context: Context, password: String?, errorLayout: TextInputLayout): Boolean {
        val error =  when {
            password.isNullOrBlank() -> context.getString(R.string.error_password_blank)
            password.length < PASSWORD_MIN -> context.getString(R.string.error_password_short)
            password.length > PASSWORD_MAX -> context.getString(R.string.error_password_long)
            password.any { !it.isLetterOrDigit() } -> context.getString(R.string.error_password_not_alphanumeric)
            else ->  null
        }

        errorLayout.error = error
        return error == null
    }
}