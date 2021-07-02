package com.indaco.daggertestapp.core.di.modules

import com.indaco.daggertestapp.core.di.DebugAllOpen
import com.indaco.daggertestapp.data.network.api.UserApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@DebugAllOpen
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideUserApi(): UserApi = UserApi()
}