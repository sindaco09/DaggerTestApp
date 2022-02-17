package com.indaco.login.di

import android.content.Context
import com.indaco.daggertestapp.core.hilt.dependencies.AppDependencies
import com.indaco.login.ui.screens.login.hilt_login.HiltLoginActivity
import com.indaco.login.ui.screens.login.hilt_login.HiltLoginActivityAndroidTest

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppDependencies::class],
    modules = [TestAccountModule::class]
)
interface TestAccountComponent {

//    fun inject(activity: HiltLoginActivityAndroidTest)
    fun inject(activity: HiltLoginActivity)
}