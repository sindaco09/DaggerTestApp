package com.indaco.daggertestapp.core.di

import com.indaco.daggertestapp.App
import com.indaco.daggertestapp.core.di.modules.AppModule
import com.indaco.daggertestapp.core.di.modules.DaoAndCacheModule
import com.indaco.daggertestapp.core.di.modules.RepositoryModule
import com.indaco.daggertestapp.core.di.modules.StorageModuleComponents

enum class DaggerInjector {
    Instance;

    private var appComponent: AppComponent? = null

    fun setupDagger() {
        // Dagger%COMPONENT_NAME%
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(App.instance))
            .daoAndCacheModule(DaoAndCacheModule())
            .repositoryModule(RepositoryModule())
            .storageModuleComponents(StorageModuleComponents())
            .build()
    }

    fun getAppComponent(): AppComponent? {
        if (appComponent == null) {
            setupDagger()
        }
        return appComponent
    }
}