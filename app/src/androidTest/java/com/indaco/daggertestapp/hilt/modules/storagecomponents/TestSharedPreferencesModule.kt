package com.indaco.daggertestapp.hilt.modules.storagecomponents

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.indaco.daggertestapp.core.hilt.modules.storagecomponents.SharedPreferencesModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Inject
import javax.inject.Singleton

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [SharedPreferencesModule::class]
//)
class TestSharedPreferencesModule {

//    @Provides
//    @Singleton
//    fun providesSharedPreferences(application: Application): SharedPreferences =
//        application.getSharedPreferences(SharedPreferencesModule.TEST_PREFS_FILE, Context.MODE_PRIVATE)

//    @Singleton
//    @Binds
//    abstract fun providesSharedPreferences(application: Application, fakeSharedPreferences: ): SharedPreferences

}

