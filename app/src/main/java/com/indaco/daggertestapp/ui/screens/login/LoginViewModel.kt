package com.indaco.daggertestapp.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indaco.daggertestapp.data.model.AuthForm
import com.indaco.daggertestapp.data.repositories.UserRepository
import com.indaco.daggertestapp.data.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private var _loginResult = MutableLiveData<User?>()
    val loginResult: LiveData<User?> get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch(dispatcher) {
            repository.login(AuthForm(email, password))
                .collect { _loginResult.postValue(it) }
        }
    }
}