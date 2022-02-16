package com.indaco.login.di

import android.app.Activity
import android.content.Context
import com.indaco.daggertestapp.core.hilt.dependencies.LoginModuleDependencies
import com.indaco.login.ui.screens.login.hilt_login.HiltLoginActivity
import dagger.hilt.android.EntryPointAccessors

object Injector {
    fun from(context: Context): AccountComponent {
        return DaggerAccountComponent
            .builder()
            .context(context)
            .appDependency(
                EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    LoginModuleDependencies::class.java
                )
            )
            .build()
    }

    fun inject(activity: Activity) =
        with(activity) {
            when (this) {
                is HiltLoginActivity -> from(this).inject(this)
            }
        }
}