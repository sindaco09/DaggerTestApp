package com.indaco.daggertestapp.core.hilt.modules.network

import com.indaco.daggertestapp.data.testmodules.network.api.bart.BartService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class BartServiceModule {
    @Provides
    fun provideBartService(retrofit: Retrofit): BartService =
        retrofit.create(BartService::class.java)
}