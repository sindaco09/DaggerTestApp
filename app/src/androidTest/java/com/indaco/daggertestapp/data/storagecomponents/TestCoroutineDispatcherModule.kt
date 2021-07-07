package com.indaco.daggertestapp.data.storagecomponents

import com.indaco.daggertestapp.core.hilt.modules.storagecomponents.CoroutineDispatcherModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoroutineDispatcherModule::class]
)
class TestCoroutineDispatcherModule {

    @ExperimentalCoroutinesApi
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = TestCoroutineDispatcher()

}