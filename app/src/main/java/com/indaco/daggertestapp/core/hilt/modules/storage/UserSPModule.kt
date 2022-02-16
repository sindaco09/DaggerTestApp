package com.indaco.daggertestapp.core.hilt.modules.storage

import android.content.SharedPreferences
import com.indaco.daggertestapp.core.hilt.DebugAllOpen
import com.indaco.daggertestapp.data.storage.cache.UserSPCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@DebugAllOpen
@Module
@InstallIn(SingletonComponent::class)
class UserSPModule {

    @Provides
    @Singleton
    fun provideUserSPModule(sp: SharedPreferences): UserSPCache = UserSPCache(sp)
}