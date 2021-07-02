package com.indaco.daggertestapp.di

import com.indaco.daggertestapp.App
import com.indaco.daggertestapp.core.di.modules.AppModule
import com.indaco.daggertestapp.core.di.modules.DaoAndCacheModule
import com.indaco.daggertestapp.core.di.modules.RepositoryModule
import com.indaco.daggertestapp.di.modules.TestStorageModuleComponents

object DaggerTestInjector {

    private var appComponent: TestAppComponent? = null

    fun setupDagger() {
        // Dagger%COMPONENT_NAME%
        appComponent = DaggerTestAppComponent.builder()
            .appModule(AppModule(App.instance))
            .daoAndCacheModule(DaoAndCacheModule())
            .repositoryModule(RepositoryModule())
            .storageModuleComponents(TestStorageModuleComponents())
            .build()
    }

    fun getAppComponent(): TestAppComponent? {
        if (appComponent == null) {
            setupDagger()
        }
        return appComponent
    }
}