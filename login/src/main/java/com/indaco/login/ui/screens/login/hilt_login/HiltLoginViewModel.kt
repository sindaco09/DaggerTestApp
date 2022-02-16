package com.indaco.login.ui.screens.login.hilt_login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indaco.daggertestapp.core.hilt.IODispatcher
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.data.repositories.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HiltLoginViewModel @Inject constructor(
    private val repository: UserRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private var _loginResult = MutableLiveData<User?>()
    val loginResult: LiveData<User?> get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch(dispatcher) {
            repository.login(email, password)
                .collect { _loginResult.postValue(it) }
        }
    }
}