package com.indaco.daggertestapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User(@PrimaryKey val email: String): Parcelable {
    fun toLoginUser(): AuthForm {
        return AuthForm(email,"")
    }

    companion object {
        const val KEY = "key_user"
    }
}