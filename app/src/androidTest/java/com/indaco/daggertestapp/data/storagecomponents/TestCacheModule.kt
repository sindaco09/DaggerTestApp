package com.indaco.daggertestapp.data.storagecomponents

import android.content.SharedPreferences
import com.indaco.daggertestapp.core.hilt.modules.CacheModule
import com.indaco.daggertestapp.data.storage.cache.UserCache
import com.indaco.daggertestapp.data.storage.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import javax.inject.Singleton

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [CacheModule::class]
//)
class TestCacheModule {

//    @Provides
//    @Singleton
//    fun provideUserCache(sp: SharedPreferences, userDao: UserDao): UserCache = mockk()
}