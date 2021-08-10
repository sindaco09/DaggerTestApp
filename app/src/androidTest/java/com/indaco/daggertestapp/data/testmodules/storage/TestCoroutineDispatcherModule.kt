package com.indaco.daggertestapp.data.testmodules.storage

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