package com.indaco.daggertestapp.di

import com.indaco.daggertestapp.core.di.AppComponent
import com.indaco.daggertestapp.core.di.modules.*
import com.indaco.daggertestapp.landing.LandingActivityTest
import com.indaco.daggertestapp.signup.SignupActivityTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    StorageModuleComponents::class,
    DaoAndCacheModule::class,
    RepositoryModule::class,
    NetworkModule::class])
interface TestAppComponent : AppComponent {
    fun inject(test: SignupActivityTest)
    fun inject(test: LandingActivityTest)
}