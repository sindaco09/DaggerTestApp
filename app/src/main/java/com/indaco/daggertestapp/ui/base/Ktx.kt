package com.indaco.daggertestapp.ui.base

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

object Ktx {
    inline fun <reified VM: ViewModel> Fragment.provideViewModel(
        noinline ownerProducer: () -> ViewModelStoreOwner = { this },
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> = OverridableLazy(viewModels(ownerProducer, factoryProducer))

    inline fun <reified VM: ViewModel> ComponentActivity.provideViewModel(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }

        return OverridableLazy(viewModels(factoryPromise))
    }
}

class OverridableLazy<T>(var implementation: Lazy<T>): Lazy<T> {
    override val value
        get() = implementation.value
    override fun isInitialized() = implementation.isInitialized()
}