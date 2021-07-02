package com.indaco.daggertestapp.core.di

import com.indaco.daggertestapp.core.di.modules.*
import com.indaco.daggertestapp.ui.screens.login.LoginActivity
import com.indaco.daggertestapp.ui.screens.landing.LandingActivity
import com.indaco.daggertestapp.ui.screens.signup.SignUpActivity
import com.indaco.daggertestapp.ui.screens.welcome.WelcomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    StorageModuleComponents::class,
    DaoAndCacheModule::class,
    RepositoryModule::class,
    NetworkModule::class])

interface AppComponent {

    fun inject(activity: SignUpActivity)
    fun inject(activity: LandingActivity)
    fun inject(activity: WelcomeActivity)
    fun inject(activity: LoginActivity)
}