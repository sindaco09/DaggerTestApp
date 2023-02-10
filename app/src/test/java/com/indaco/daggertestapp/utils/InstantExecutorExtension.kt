package com.indaco.daggertestapp.utils

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.setMain
//import org.junit.jupiter.api.extension.AfterEachCallback
//import org.junit.jupiter.api.extension.BeforeEachCallback
//import org.junit.jupiter.api.extension.ExtensionContext

/*
 * https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
 */
//@ExperimentalCoroutinesApi
//class InstantExecutorExtension: BeforeEachCallback, AfterEachCallback {
//    override fun beforeEach(p0: ExtensionContext?) {
//        Dispatchers.setMain(TestCoroutineDispatcher())
//        ArchTaskExecutor.getInstance()
//            .setDelegate(object : TaskExecutor() {
//                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()
//
//                override fun postToMainThread(runnable: Runnable) = runnable.run()
//
//                override fun isMainThread(): Boolean = true
//            })
//    }
//
//    override fun afterEach(p0: ExtensionContext?) {
//        ArchTaskExecutor.getInstance().setDelegate(null)
//        Dispatchers.resetMain()
//    }
//}