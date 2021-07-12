package com.indaco.daggertestapp.ui.screens.helper

import androidx.lifecycle.ViewModel
import com.indaco.daggertestapp.ui.base.OverridableLazy
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.isAccessible

object ViewModelHelper {
    fun <VM: ViewModel, T> T.replace(
        viewModelDelegate: KProperty1<T, VM>, viewModel: VM) {
        viewModelDelegate.isAccessible = true
        (viewModelDelegate.getDelegate(this) as
                OverridableLazy<VM>).implementation = lazy { viewModel }
    }
}