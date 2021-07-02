package com.indaco.daggertestapp.core.di.modules

import android.content.SharedPreferences
import com.indaco.daggertestapp.core.di.DebugAllOpen
import com.indaco.daggertestapp.core.room.AppDatabase
import com.indaco.daggertestapp.data.storage.cache.UserCache
import com.indaco.daggertestapp.data.storage.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@DebugAllOpen
@Module
class DaoAndCacheModule {

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao =
        appDatabase.userDao()

    @Provides
    @Singleton
    fun provideUserCache(userDao: UserDao, sp: SharedPreferences): UserCache =
        UserCache(userDao, sp)

}