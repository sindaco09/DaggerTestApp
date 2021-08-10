package com.indaco.daggertestapp.core.hilt.modules.storage

import android.content.SharedPreferences
import com.indaco.daggertestapp.core.hilt.DebugAllOpen
import com.indaco.daggertestapp.data.testmodules.storage.cache.UserCache
import com.indaco.daggertestapp.data.testmodules.storage.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@DebugAllOpen
@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    @Singleton
    fun provideUserCache(userDao: UserDao, sp: SharedPreferences): UserCache =
        UserCache(userDao, sp)
}