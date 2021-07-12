package com.indaco.daggertestapp.ui.screens.onboarding.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indaco.daggertestapp.data.model.AuthForm
import com.indaco.daggertestapp.data.model.User
import com.indaco.daggertestapp.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _registerResult = MutableLiveData<User?>()
    val registerResult: LiveData<User?> = _registerResult

    fun register(authForm: AuthForm) {
        viewModelScope.launch(dispatcher) {
            userRepository.register(authForm.toUser()).collect {
                _registerResult.postValue(it)
            }
        }
    }
}