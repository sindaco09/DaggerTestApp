package com.indaco.daggertestapp.core.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.indaco.daggertestapp.core.di.DebugAllOpen
import com.indaco.daggertestapp.core.room.AppDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/*
 * This is for storing components that aren't "public" like a Repository or Dao class but only used
 * internally by those public facing classes
 */
@DebugAllOpen
@Module
class StorageModuleComponents {

    companion object {
        const val PREFS_FILE = "MyPrefs"
        const val TEST_PREFS_FILE = "MyPrefsTest"
        const val DB_FILE = "database"
        const val DB_TEST_FILE = "test_database"
    }

    @Provides
    @Singleton
    fun provideDb(application: Application): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, DB_FILE)
        .fallbackToDestructiveMigration() //clear db on version increase
        .build()

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}