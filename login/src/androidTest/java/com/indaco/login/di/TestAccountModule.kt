package com.indaco.login.di

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.indaco.daggertestapp.core.hilt.viewmodel.ViewModelFactory
import com.indaco.daggertestapp.core.hilt.viewmodel.ViewModelKey
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.data.repositories.UserRepository
import com.indaco.login.ui.screens.login.hilt_login.HiltLoginViewModel
import com.indaco.testutils.utils.Const.EMAIL_VALID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class TestAccountModule {

    @Provides
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @IntoMap
    @ViewModelKey(HiltLoginViewModel::class)
    fun provideHiltLoginViewModel(viewmodel: HiltLoginViewModel): ViewModel =
        FakeHiltLoginViewModel()
}

class FakeHiltLoginViewModel: HiltLoginViewModel(mockkClass(UserRepository::class), Dispatchers.IO) {

    override fun login(email: String, password: String) {
        Log.d("TAG","fake login")
        if (email == EMAIL_VALID)
            _loginResult.postValue(User(EMAIL_VALID))
        else
            _loginResult.postValue(null)
    }
}