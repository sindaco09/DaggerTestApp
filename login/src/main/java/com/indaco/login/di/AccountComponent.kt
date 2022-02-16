package com.indaco.login.di

import android.content.Context
import com.indaco.daggertestapp.core.hilt.dependencies.AppDependencies
import com.indaco.login.ui.screens.login.hilt_login.HiltLoginActivity

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppDependencies::class],
    modules = [AccountModule::class]
)
interface AccountComponent {

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependency(d: AppDependencies): Builder
        fun build(): AccountComponent
    }

    fun inject(activity: HiltLoginActivity)

}