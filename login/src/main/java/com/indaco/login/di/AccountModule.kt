package com.indaco.login.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.indaco.login.ui.screens.login.hilt_login.HiltLoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.indaco.daggertestapp.core.hilt.viewmodel.ViewModelFactory
import com.indaco.daggertestapp.core.hilt.viewmodel.ViewModelKey
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@InstallIn(SingletonComponent::class)
//@Module
abstract class AccountModule {

//    @Binds
//    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(HiltLoginViewModel::class)
//    abstract fun provideRemoteLoginViewModel(viewmodel: HiltLoginViewModel): ViewModel
}