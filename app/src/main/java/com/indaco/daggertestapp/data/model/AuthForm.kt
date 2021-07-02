package com.indaco.daggertestapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthForm(var email: String, var password: String): Parcelable {
    fun toUser(): User = User(email)
}