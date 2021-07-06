package com.indaco.daggertestapp.hilt.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.indaco.daggertestapp.core.hilt.modules.StorageModuleComponents
import com.indaco.daggertestapp.core.hilt.modules.StorageModuleComponents.Companion.DB_TEST_FILE
import com.indaco.daggertestapp.core.hilt.modules.StorageModuleComponents.Companion.TEST_PREFS_FILE
import com.indaco.daggertestapp.core.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [StorageModuleComponents::class]
)
class TestStorageModuleComponents {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(TEST_PREFS_FILE, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideDb(application: Application): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, DB_TEST_FILE)
        .fallbackToDestructiveMigration() //clear db on version increase
        .build()

    @Provides
    @ExperimentalCoroutinesApi
    fun provideIoDispatcher(): CoroutineDispatcher {
        return TestCoroutineDispatcher()
    }
}