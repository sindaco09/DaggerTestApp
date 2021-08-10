package com.indaco.daggertestapp.core.hilt.modules.storage.components

import com.indaco.daggertestapp.core.hilt.DebugAllOpen
import com.indaco.daggertestapp.core.room.AppDatabase
import com.indaco.daggertestapp.data.testmodules.storage.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@DebugAllOpen
@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao =
        appDatabase.userDao()

}