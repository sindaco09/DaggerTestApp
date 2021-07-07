package com.indaco.daggertestapp.hilt.modules.storagecomponents

import android.app.Application
import androidx.room.Room
import com.indaco.daggertestapp.core.hilt.modules.storagecomponents.DatabaseModule
import com.indaco.daggertestapp.core.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class TestDatabaseModule {

    @Provides
    @Singleton
    fun provideDb(application: Application): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, DatabaseModule.DB_TEST_FILE)
        .fallbackToDestructiveMigration() //clear db on version increase
        .build()
}