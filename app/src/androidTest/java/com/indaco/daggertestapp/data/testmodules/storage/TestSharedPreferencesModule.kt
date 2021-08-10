package com.indaco.daggertestapp.data.testmodules.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.indaco.daggertestapp.core.hilt.modules.storage.components.SharedPreferencesModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/*
 * replace PREFS_FILE with TEST_PREFS_FILE
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [SharedPreferencesModule::class]
)
class TestSharedPreferencesModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(SharedPreferencesModule.TEST_PREFS_FILE, Context.MODE_PRIVATE)

}

