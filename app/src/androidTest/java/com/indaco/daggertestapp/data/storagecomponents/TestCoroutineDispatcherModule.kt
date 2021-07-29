package com.indaco.daggertestapp.data.storagecomponents

import com.indaco.daggertestapp.core.hilt.modules.storage.components.CoroutineDispatcherModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

/*
 * replace Dispatchers.IO with TestCoroutineDispatchers. use only a main and separate dispatchers
 * with 1 worker thread vs many.
 *
 * Question: is it better to test with the same dispatcher as the main app? would it work?
 * rules seem a bit different for UI tests vs actual app
 */
//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [CoroutineDispatcherModule::class]
//)
class TestCoroutineDispatcherModule {

//    @ExperimentalCoroutinesApi
//    @Provides
//    fun provideIoDispatcher(): CoroutineDispatcher = TestCoroutineDispatcher()

}