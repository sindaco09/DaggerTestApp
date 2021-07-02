package com.indaco.daggertestapp

import android.app.Application
import com.indaco.daggertestapp.core.di.modules.AppModule
import com.indaco.daggertestapp.core.di.AppComponent
import com.indaco.daggertestapp.core.di.DaggerInjector

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        DaggerInjector.Instance.setupDagger()
    }

}