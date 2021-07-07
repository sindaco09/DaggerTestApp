package com.indaco.daggertestapp.core.hilt.modules.storagecomponents

import com.indaco.daggertestapp.core.hilt.DebugAllOpen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@DebugAllOpen
@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatcherModule {

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}