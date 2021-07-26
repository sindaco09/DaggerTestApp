package com.indaco.daggertestapp.core.hilt.modules.storage.components

import android.app.Application
import androidx.room.Room
import com.indaco.daggertestapp.core.hilt.DebugAllOpen
import com.indaco.daggertestapp.core.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@DebugAllOpen
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    companion object {
        const val DB_FILE = "database"
        const val DB_TEST_FILE = "test_database"
    }

    @Provides
    @Singleton
    fun provideDb(application: Application): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, DB_FILE)
        .fallbackToDestructiveMigration() //clear db on version increase
        .build()
}