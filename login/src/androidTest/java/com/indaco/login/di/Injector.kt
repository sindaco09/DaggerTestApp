package com.indaco.login.di

import android.app.Activity
import androidx.test.core.app.ApplicationProvider
import com.indaco.daggertestapp.core.hilt.dependencies.AppDependencies
import com.indaco.login.ui.screens.login.hilt_login.HiltLoginActivity
import dagger.hilt.android.EntryPointAccessors

object Injector {
    fun from(): TestAccountComponent {
        return DaggerTestAccountComponent
            .builder()
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    ApplicationProvider.getApplicationContext(),
                    AppDependencies::class.java
                )
            )
            .build()
    }

    fun inject(activity: Activity) =
        with(activity) {
            when (this) {
                is HiltLoginActivity -> from().inject(this)
            }
        }
}