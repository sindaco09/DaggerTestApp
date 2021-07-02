package com.indaco.daggertestapp.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.indaco.daggertestapp.core.di.modules.StorageModuleComponents
import com.indaco.daggertestapp.core.room.AppDatabase

class TestStorageModuleComponents: StorageModuleComponents() {

    override fun providesSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(TEST_PREFS_FILE, Context.MODE_PRIVATE)

    override fun provideDb(application: Application): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, DB_TEST_FILE)
        .fallbackToDestructiveMigration() //clear db on version increase
        .build()
}